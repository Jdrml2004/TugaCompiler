// src/TypeChecking/RefPhase.java
package TypeChecking;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import Tuga.TugaBaseVisitor;
import Tuga.TugaParser;
import SymbolTable.FunctionSymbol;
import SymbolTable.Scope;
import SymbolTable.Symbol;
import SymbolTable.VariableSymbol;

public class RefPhase extends TugaBaseVisitor<Void> {
    private final Scope global;
    private final ParseTreeProperty<Scope> scopes;
    private Scope currentScope;

    public RefPhase(Scope global, ParseTreeProperty<Scope> scopes) {
        this.global = global;
        this.scopes = scopes;
    }

    @Override
    public Void visitProg(TugaParser.ProgContext ctx) {
        currentScope = global;
        return super.visitProg(ctx);
    }

    @Override
    public Void visitFunctionDecl(TugaParser.FunctionDeclContext ctx) {
        currentScope = scopes.get(ctx);
        super.visitFunctionDecl(ctx);
        currentScope = global;
        return null;
    }

    @Override
    public Void visitBlock(TugaParser.BlockContext ctx) {
        currentScope = scopes.get(ctx);
        super.visitBlock(ctx);
        currentScope = currentScope.getEnclosingScope();
        return null;
    }

    @Override
    public Void visitIdentExpr(TugaParser.IdentExprContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = currentScope.resolve(name);
        if (sym == null) {
            System.out.printf("erro na linha %d: variável '%s' não declarada%n",
                    ctx.IDENT().getSymbol().getLine(), name);
        }
        return null;
    }

    @Override
    public Void visitFunctionCallExpr(TugaParser.FunctionCallExprContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = currentScope.resolve(name);
        if (sym == null) {
            System.out.printf("erro na linha %d: nenhuma função '%s'%n",
                    ctx.IDENT().getSymbol().getLine(), name);
        } else if (!(sym instanceof FunctionSymbol)) {
            System.out.printf("erro na linha %d: '%s' não é função%n",
                    ctx.IDENT().getSymbol().getLine(), name);
        }
        // visita os argumentos
        for (var e : ctx.expr()) visit(e);
        return null;
    }
}
