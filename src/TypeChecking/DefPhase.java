package TypeChecking;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import Tuga.TugaBaseVisitor;
import Tuga.TugaParser;
import SymbolTable.FunctionSymbol;
import SymbolTable.Scope;
import SymbolTable.Symbol;
import SymbolTable.VariableSymbol;

public class DefPhase extends TugaBaseVisitor<Void> {
    public final ParseTreeProperty<Scope> scopes = new ParseTreeProperty<>();
    public Scope global;
    private Scope currentScope;
    private FunctionSymbol currentFunction;
    private boolean principalDefined = false;

    private Symbol.Type mapType(String txt) {
        return switch (txt) {
            case "inteiro"   -> Symbol.Type.tINT;
            case "real"      -> Symbol.Type.tFLOAT;
            case "booleano"  -> Symbol.Type.tBOOLEAN;
            case "string"    -> Symbol.Type.tSTRING;
            default           -> Symbol.Type.tVOID;
        };
    }

    @Override
    public Void visitProg(TugaParser.ProgContext ctx) {
        global = new Scope(null, "global");
        currentScope = global;

        // 1) Registra assinaturas de funções e detecta duplicatas:
        for (TugaParser.FunctionDeclContext f : ctx.functionDecl()) {
            String name = f.IDENT().getText();
            if (global.resolve(name) != null) {
                int line = f.IDENT().getSymbol().getLine();
                System.out.printf("erro na linha %d: '%s' ja foi declarado%n", line, name);
            } else {
                Symbol.Type ret = f.type() != null
                        ? mapType(f.type().getText())
                        : Symbol.Type.tVOID;
                FunctionSymbol fn = new FunctionSymbol(f.IDENT().getSymbol(), ret);
                global.define(fn);
            }
            if (name.equals("principal")) {
                principalDefined = true;
            }
        }

        // 2) Processa cada função
        for (TugaParser.FunctionDeclContext f : ctx.functionDecl()) {
            visitFunctionDecl(f);
        }

        // 3) Declarações globais (variáveis) e detecta duplicatas:
        for (TugaParser.DeclarationContext d : ctx.declaration()) {
            visitDeclaration(d);
        }

        // 4) Verifica existência de principal()
        if (!principalDefined) {
            int line = ctx.functionDecl(ctx.functionDecl().size() - 1).stop.getLine() + 1;
            System.out.printf("erro na linha %d: falta funcao principal()%n", line);
        }

        return null;
    }

    @Override
    public Void visitFunctionDecl(TugaParser.FunctionDeclContext ctx) {
        // Recupera o FunctionSymbol já registrado
        currentFunction = (FunctionSymbol) global.resolve(ctx.IDENT().getText());

        // Abre escopo da função
        currentScope = new Scope(currentScope, currentFunction.lexeme());
        scopes.put(ctx, currentScope);

        // Parâmetros (verifica duplicatas no mesmo escopo)
        for (TugaParser.ParamContext p : ctx.param()) {
            String pname = p.IDENT().getText();
            Token t = p.IDENT().getSymbol();
            Symbol.Type pt = mapType(p.type().getText());

            // Se já existe algo com esse nome (no próprio currentScope ou pai), é duplicata
            if (currentScope.resolve(pname) != null) {
                System.out.printf("erro na linha %d: '%s' ja foi declarado%n", t.getLine(), pname);
            } else {
                VariableSymbol vs = new VariableSymbol(t, pt);
                currentFunction.add_argument(vs);
                currentScope.define(vs);
            }
        }

        // Corpo
        visit(ctx.block());

        // Fecha escopo
        currentScope = currentScope.getEnclosingScope();
        currentFunction = null;
        return null;
    }

    @Override
    public Void visitDeclaration(TugaParser.DeclarationContext ctx) {
        Symbol.Type t = mapType(ctx.type().getText());
        for (var id : ctx.identList().IDENT()) {
            String varName = id.getText();
            Token tok = id.getSymbol();

            // Se existe qualquer símbolo com esse nome no escopo atual ou pai, é duplicata
            if (currentScope.resolve(varName) != null) {
                System.out.printf("erro na linha %d: '%s' ja foi declarado%n", tok.getLine(), varName);
            } else {
                VariableSymbol vs = new VariableSymbol(tok, t);
                currentScope.define(vs);
            }
        }
        return null;
    }

    @Override
    public Void visitBlock(TugaParser.BlockContext ctx) {
        Scope saved = currentScope;
        currentScope = new Scope(saved, saved.getName());
        scopes.put(ctx, currentScope);

        // Declarações e statements
        for (var d : ctx.declaration()) visitDeclaration(d);
        for (var s : ctx.stat())        visit(s);

        currentScope = saved;
        return null;
    }
}
