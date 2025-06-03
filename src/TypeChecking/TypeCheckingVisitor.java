package TypeChecking;

import SymbolTable.*;
import Tuga.TugaBaseVisitor;
import Tuga.TugaParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeCheckingVisitor extends TugaBaseVisitor<Type> {
    private final Scope globalScope;
    private final ParseTreeProperty<Scope> scopes;
    private Scope currentScope;
    private final Map<ParseTree, Type> nodeTypes = new HashMap<>();
    private boolean typeErrors = false;
    private Type currentFunctionReturn = Type.NULO;

    public TypeCheckingVisitor(Scope globalScope, ParseTreeProperty<Scope> scopes) {
        this.globalScope = globalScope;
        this.scopes = scopes;
    }
    public Map<ParseTree, Type> getNodeTypes() {
        return nodeTypes;
    }
    public boolean getTypeCheckingErrors() {
        return typeErrors;
    }
    private Type record(ParseTree ctx, Type t) {
        nodeTypes.put(ctx, t);
        return t;
    }
    private void error(Token t, String msg) {
        System.out.println("erro na linha " + t.getLine() + ": " + msg);
        typeErrors = true;
    }

    private Type mapType(String txt) {
        return switch (txt) {
            case "inteiro"   -> Type.INT;
            case "real"      -> Type.REAL;
            case "booleano"  -> Type.BOOL;
            case "string"    -> Type.STRING;
            default           -> Type.NULO;
        };
    }

    private Type mapSymType(Symbol.Type st) {
        return switch (st) {
            case tINT     -> Type.INT;
            case tFLOAT   -> Type.REAL;
            case tBOOLEAN -> Type.BOOL;
            case tSTRING  -> Type.STRING;
            default       -> Type.NULO;
        };
    }

    @Override
    public Type visitProg(TugaParser.ProgContext ctx) {
        currentScope = globalScope;
        return super.visitProg(ctx);
    }

    @Override
    public Type visitFunctionDecl(TugaParser.FunctionDeclContext ctx) {
        currentScope = scopes.get(ctx);
        currentFunctionReturn = ctx.type()!=null
                ? mapType(ctx.type().getText())
                : Type.NULO;
        visit(ctx.block());
        currentScope = globalScope;
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitBlock(TugaParser.BlockContext ctx) {
        Scope saved = currentScope;
        currentScope = scopes.get(ctx);
        for (var d : ctx.declaration()) visit(d);
        for (var s : ctx.stat()) visit(s);
        currentScope = saved;
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitDeclaration(TugaParser.DeclarationContext ctx) {
        Type t = mapType(ctx.type().getText());
        if (t == Type.NULO) {
            error(ctx.type().getStart(), "tipo invalido: " + ctx.type().getText());
        }
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitBlockStat(TugaParser.BlockStatContext ctx) {
        Scope saved = currentScope;
        currentScope = scopes.get(ctx.block());
        for (var d : ctx.block().declaration()) visit(d);
        for (var s : ctx.block().stat())        visit(s);
        currentScope = saved;
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitAssignStat(TugaParser.AssignStatContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = currentScope.resolve(name);
        if (sym == null) {
            error(ctx.IDENT().getSymbol(), "variavel nao declarada: " + name);
            visit(ctx.expr());
            return record(ctx, Type.NULO);
        }
        if (!(sym instanceof VariableSymbol)) {
            error(ctx.IDENT().getSymbol(), "'" + name + "' nao eh variavel");
            visit(ctx.expr());
            return record(ctx, Type.NULO);
        }

        Type varT = mapSymType(((VariableSymbol) sym).type);
        Type exprT = visit(ctx.expr());

        if (exprT == Type.NULO) {
            return record(ctx, Type.NULO);
        }

        boolean ok = exprT == varT || (varT == Type.REAL && exprT == Type.INT);
        if (!ok) {
            error(ctx.IDENT().getSymbol(),
                    "operador '<-' eh invalido entre " + varT + " e " + exprT);
        }
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitWriteStat(TugaParser.WriteStatContext ctx) {
        visit(ctx.expr());
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitReturnStat(TugaParser.ReturnStatContext ctx) {
        if (ctx.expr() != null) {
            Type got = visit(ctx.expr());
            if (got == Type.INT && currentFunctionReturn == Type.REAL) {
                return record(ctx, Type.REAL);
            }
            if (got != currentFunctionReturn) {
                error(ctx.start,
                        "return: esperado " + currentFunctionReturn + " mas obteve " + got);
            }
        } else if (currentFunctionReturn != Type.NULO) {
            error(ctx.start, "return sem valor em funcao nao-void");
        }
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitWhileStat(TugaParser.WhileStatContext ctx) {
        Type c = visit(ctx.expr());
        if (c != Type.BOOL) {
            error(ctx.expr().getStart(), "condicao de enquanto nao-booleano");
        }
        visit(ctx.stat());
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitIfStat(TugaParser.IfStatContext ctx) {
        Type c = visit(ctx.expr());
        if (c != Type.BOOL) {
            error(ctx.expr().getStart(), "condicao de se nao-booleano");
        }
        visit(ctx.stat(0));
        if (ctx.stat().size()>1) visit(ctx.stat(1));
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitEmptyStat(TugaParser.EmptyStatContext ctx) {
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitIdentExpr(TugaParser.IdentExprContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = currentScope.resolve(name);
        if (sym == null) {
            error(ctx.IDENT().getSymbol(), "variavel nao declarada: " + name);
            return record(ctx, Type.NULO);
        }
        if (!(sym instanceof VariableSymbol)) {
            error(ctx.IDENT().getSymbol(), "'" + name + "' nao eh variavel");
            return record(ctx, Type.NULO);
        }
        Type t = mapSymType(((VariableSymbol) sym).type);
        return record(ctx, t);
    }


    @Override
    public Type visitCallStat(TugaParser.CallStatContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = currentScope.resolve(name);
        if (sym == null) {
            error(ctx.IDENT().getSymbol(), "funcao nao declarada: " + name);
            ctx.expr().forEach(this::visit);
            return record(ctx, Type.NULO);
        }
        if (!(sym instanceof FunctionSymbol)) {
            error(ctx.IDENT().getSymbol(), "'" + name + "' nao eh funcao");
            ctx.expr().forEach(this::visit);
            return record(ctx, Type.NULO);
        }
        FunctionSymbol fn = (FunctionSymbol) sym;
        List<TugaParser.ExprContext> args = ctx.expr();

        int expected = fn.get_arguments().size();
        int given    = args.size();
        if (given != expected) {
            error(ctx.IDENT().getSymbol(),
                    "'" + name + "' requer " + expected + " argumentos");
            ctx.expr().forEach(this::visit);
            return record(ctx, Type.NULO);
        }

        for (int i = 0; i < given; i++) {
            Type argT = visit(args.get(i));
            Symbol.Type paramSt = fn.get_arguments().get(i).type;
            Type paramT = mapSymType(paramSt);
            if (argT != paramT) {
                if (!(paramT == Type.REAL && argT == Type.INT)) {
                    Token tok = args.get(i).getStart();
                    if(paramT == Type.STRING){
                        error(tok,
                                "'" + args.get(i).getText() + "' devia ser do tipo string");
                        return record(ctx, Type.NULO);
                    }
                    error(tok,
                            "'" + args.get(i).getText() + "' devia ser do tipo " + paramT);
                    return record(ctx, Type.NULO);
                }
            }
        }

        Type ret = mapSymType(fn.type);
        if (ret != Type.NULO) {
            error(ctx.IDENT().getSymbol(),
                    "valor de '" + name + "' tem de ser atribuido a uma variavel");
        }
        return record(ctx, Type.NULO);
    }


    @Override
    public Type visitFunctionCallExpr(TugaParser.FunctionCallExprContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = currentScope.resolve(name);
        ctx.expr().forEach(this::visit);

        if (sym == null) {
            error(ctx.IDENT().getSymbol(), "funcao nao declarada: " + name);
            return record(ctx, Type.NULO);
        }
        if (!(sym instanceof FunctionSymbol)) {
            error(ctx.IDENT().getSymbol(), "'" + name + "' nao eh funcao");
            return record(ctx, Type.NULO);
        }
        FunctionSymbol fn = (FunctionSymbol) sym;

        int expected = fn.get_arguments().size();
        int given    = ctx.expr().size();
        if (given != expected) {
            error(ctx.IDENT().getSymbol(),
                    "'" + name + "' requer " + expected + " argumentos");
            return record(ctx, Type.NULO);
        }

        for (int i = 0; i < given; i++) {
            Type argT = nodeTypes.get(ctx.expr(i));
            Symbol.Type paramSt = fn.get_arguments().get(i).type;
            Type paramT = mapSymType(paramSt);
            if (argT != paramT && !(paramT == Type.REAL && argT == Type.INT)) {
                Token tok = ctx.expr(i).getStart();
                error(tok, "'" + ctx.expr(i).getText() + "' devia ser do tipo " + paramT);
                return record(ctx, Type.NULO);
            }
        }

        Type ret = mapSymType(fn.type);
        return record(ctx, ret);
    }


    @Override public Type visitIntExpr(TugaParser.IntExprContext ctx)      { return record(ctx, Type.INT); }
    @Override public Type visitDoubleExpr(TugaParser.DoubleExprContext ctx){ return record(ctx, Type.REAL); }
    @Override public Type visitStringExpr(TugaParser.StringExprContext ctx){ return record(ctx, Type.STRING); }
    @Override public Type visitBooleanExpr(TugaParser.BooleanExprContext ctx){ return record(ctx, Type.BOOL); }
    @Override public Type visitParensExpr(TugaParser.ParensExprContext ctx) { return record(ctx, visit(ctx.expr())); }

    @Override
    public Type visitUnaryExpr(TugaParser.UnaryExprContext ctx) {
        String op = ctx.op.getText();
        Type v = visit(ctx.expr());
        if (op.equals("-")) {
            if (v == Type.INT || v == Type.REAL) return record(ctx, v);
            error(ctx.start, "'-' aplicado a nao numerico: " + v);
        } else {
            if (v == Type.BOOL) return record(ctx, Type.BOOL);
            error(ctx.start, "'nao' aplicado a nao-booleano: " + v);
        }
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitMulDivExpr(TugaParser.MulDivExprContext ctx) {
        String op = ctx.op.getText();
        Type l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if ((l == Type.INT || l == Type.REAL) && (r == Type.INT || r == Type.REAL)) {
            Type res = (l == Type.REAL || r == Type.REAL) ? Type.REAL : Type.INT;
            if (op.equals("%") && res == Type.REAL) {
                error(ctx.start, "'%' so para inteiros");
                return record(ctx, Type.NULO);
            }
            return record(ctx, res);
        }
        error(ctx.start, "'" + op + "' invalido entre " + l + " e " + r);
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitAddSubExpr(TugaParser.AddSubExprContext ctx) {
        String op = ctx.op.getText();
        Type l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if ((l == Type.INT || l == Type.REAL) && (r == Type.INT || r == Type.REAL)) {
            return record(ctx, (l == Type.REAL || r == Type.REAL) ? Type.REAL : Type.INT);
        }
        if (op.equals("+") && (l == Type.STRING || r == Type.STRING)) {
            return record(ctx, Type.STRING);
        }
        String lt = "";
        String rt = "";
        if(l == Type.NULO) lt = "vazio";
        if(r == Type.INT) rt = "inteiro";
        error(ctx.start, "'" + op + "' invalido entre " + lt + " e " + rt);
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitRelationalExpr(TugaParser.RelationalExprContext ctx) {
        Type l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if ((l == Type.INT || l == Type.REAL) && (r == Type.INT || r == Type.REAL)) {
            return record(ctx, Type.BOOL);
        }
        error(ctx.start, "relacional '" + ctx.op.getText() + "' invalido entre " + l + " e " + r);
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitEqualityExpr(TugaParser.EqualityExprContext ctx) {
        Type l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if ((l == Type.INT || l == Type.REAL) && (r == Type.INT || r == Type.REAL)) {
            return record(ctx, Type.BOOL);
        }
        if (l != Type.NULO && l == r) {
            return record(ctx, Type.BOOL);
        }
        error(ctx.start, "igualdade '" + ctx.op.getText() + "' invalido entre " + l + " e " + r);
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitAndExpr(TugaParser.AndExprContext ctx) {
        Type l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if (l == Type.BOOL && r == Type.BOOL) return record(ctx, Type.BOOL);
        error(ctx.start, "'e' invalido entre " + l + " e " + r);
        return record(ctx, Type.NULO);
    }

    @Override
    public Type visitOrExpr(TugaParser.OrExprContext ctx) {
        Type l = visit(ctx.expr(0)), r = visit(ctx.expr(1));
        if (l == Type.BOOL && r == Type.BOOL) return record(ctx, Type.BOOL);
        error(ctx.start, "'ou' invalido entre " + l + " e " + r);
        return record(ctx, Type.NULO);
    }
}
