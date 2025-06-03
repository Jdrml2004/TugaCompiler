package CodeGenerator;

import SymbolTable.*;
import Tuga.TugaBaseVisitor;
import Tuga.TugaParser;
import TypeChecking.Type;
import VM.OpCode;
import VM.Instruction.Instruction;
import VM.Instruction.Instruction1Arg;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;


import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class CodeGen extends TugaBaseVisitor<Void> {
    private final List<Instruction> code = new ArrayList<>();
    private final Map<ParseTree, Type> tipos;
    private final ConstantPool constPool;
    private final ParseTreeProperty<Scope> scopes;
    private final Scope globalScope;

    private final Map<String, Integer> functionAddresses = new HashMap<>();
    private final Map<String, List<Integer>> pendingPatches = new HashMap<>();

    private final Map<String, Integer> variablesValue = new HashMap<>();
    private int nextGlobalIndex = 0;

    private FunctionSymbol currentFunction = null;
    private int paramCount = 0;
    private int storeIdx = 2;

    public CodeGen(Map<ParseTree, Type> tipos,
                   ConstantPool constPool,
                   ParseTreeProperty<Scope> scopes,
                   Scope globalScope) {
        this.tipos = tipos;
        this.constPool = constPool;
        this.scopes = scopes;
        this.globalScope = globalScope;
    }

    private void emit(OpCode opc) {
        code.add(new Instruction(opc));
    }

    private void emit(OpCode opc, int arg) {
        code.add(new Instruction1Arg(opc, arg));
    }


    @Override
    public Void visitProg(TugaParser.ProgContext ctx) {
        emit(OpCode.call, 0);
        Instruction1Arg mainCall = (Instruction1Arg) code.get(code.size() - 1);
        emit(OpCode.halt);

        for (var f : ctx.functionDecl()) {
            functionAddresses.put(f.IDENT().getText(), code.size());
            visitFunctionDecl(f);
        }
        for (var d : ctx.declaration()) {
            visitDeclaration(d);
        }
        for (var entry : pendingPatches.entrySet()) {
            int addr = functionAddresses.get(entry.getKey());
            for (int idx : entry.getValue()) {
                ((Instruction1Arg) code.get(idx)).setArg(addr);
            }
        }
        mainCall.setArg(functionAddresses.get("principal"));
        return null;
    }

    @Override
    public Void visitFunctionDecl(TugaParser.FunctionDeclContext ctx) {
        currentFunction = (FunctionSymbol) globalScope.resolve(ctx.IDENT().getText());
        paramCount = ctx.param() == null ? 0 : ctx.param().size();
        storeIdx = 2;
        variablesValue.clear();
        for (int i = 0; i < paramCount; i++) {
            String name = ctx.param().get(i).IDENT().getText();
            variablesValue.put(name, i - paramCount);
        }
        visit(ctx.block());
        int localsCount = storeIdx - (paramCount + 2);
        if (ctx.type() == null) {
            if (localsCount > 0) {
                emit(OpCode.pop, localsCount);
            }
            emit(OpCode.ret, paramCount);
        }
        currentFunction = null;
        return null;
    }


    @Override
    public Void visitReturnStat(TugaParser.ReturnStatContext ctx) {
        // 1) Gera código para a expressão de retorno (se houver)
        Type exprType = Type.NULO;
        if (ctx.expr() != null) {
            visit(ctx.expr());
            exprType = tipos.get(ctx.expr());
        }

        // 2) Descobre o tipo de retorno declarado na função atual
        Symbol.Type declared = currentFunction.type;            // enum Symbol.Type
        Type declaredType = switch (declared) {
            case tINT     -> Type.INT;
            case tFLOAT   -> Type.REAL;
            case tBOOLEAN -> Type.BOOL;
            case tSTRING  -> Type.STRING;
            default       -> Type.NULO;
        };

        // 3) Se a função declarou "real" mas recebemos um "int", fazemos itod
        if (declaredType == Type.REAL && exprType == Type.INT) {
            emit(OpCode.itod);
        }

        // 4) Pop das variáveis locais (já gerenciado em visitFunctionDecl) e emite retval
        //    (esperava-se que 'paramCount' estivesse no escopo desta função).
        emit(OpCode.retval, paramCount);
        return null;
    }

    @Override
    public Void visitDeclaration(TugaParser.DeclarationContext ctx) {
        var ids = ctx.identList().IDENT();
        int n = ids.size();
        if (currentFunction == null) {
            emit(OpCode.galloc, n);
            for (int i = 0; i < n; i++) {
                variablesValue.put(ids.get(i).getText(), nextGlobalIndex + i);
            }
            nextGlobalIndex += n;
        } else {
            emit(OpCode.lalloc, n);
            for (int i = 0; i < n; i++) {
                variablesValue.put(ids.get(i).getText(), storeIdx + i);
            }
            storeIdx += n;
        }
        return null;
    }

    @Override
    public Void visitBlock(TugaParser.BlockContext ctx) {
        int savedStoreIdx = storeIdx;

        for (var d : ctx.declaration()) visitDeclaration(d);
        for (var s : ctx.stat())        visit(s);

        if (currentFunction != null && currentFunction.lexeme().equals("principal")) {
            int newLocals = storeIdx - savedStoreIdx;
            if (newLocals > 0) {
                emit(OpCode.pop, newLocals);
            }
            storeIdx = savedStoreIdx;
        }
        return null;
    }

    @Override
    public Void visitWriteStat(TugaParser.WriteStatContext ctx) {
        visit(ctx.expr());
        switch (tipos.get(ctx.expr())) {
            case INT:    emit(OpCode.iprint); break;
            case REAL:   emit(OpCode.dprint); break;
            case STRING: emit(OpCode.sprint); break;
            case BOOL:   emit(OpCode.bprint); break;
        }
        return null;
    }

    @Override
    public Void visitCallStat(TugaParser.CallStatContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = globalScope.resolve(name);
        if (!(sym instanceof FunctionSymbol)) {
            throw new RuntimeException("'" + name + "' não é FunctionSymbol");
        }
        FunctionSymbol function = (FunctionSymbol) sym;
        List<TugaParser.ExprContext> args = ctx.expr();
        int nArgs = args.size();
        List<Symbol> formals = function.get_arguments();

        // 1) Empilha argumentos, com cast INT→REAL se necessário
        for (int i = 0; i < nArgs; i++) {
            TugaParser.ExprContext argCtx = args.get(i);
            visit(argCtx);

            Type argType = tipos.get(argCtx);
            VariableSymbol vs = (VariableSymbol) formals.get(i);
            Type paramType = mapSymType(vs.type);
            if (paramType == Type.REAL && argType == Type.INT) {
                emit(OpCode.itod);
            }
        }
        if (functionAddresses.containsKey(name)) {
            emit(OpCode.call, functionAddresses.get(name));
        } else {
            emit(OpCode.call, 0);
            pendingPatches
                    .computeIfAbsent(name, k -> new ArrayList<>())
                    .add(code.size() - 1);
        }
        return null;
    }

    @Override
    public Void visitAssignStat(TugaParser.AssignStatContext ctx) {
        visit(ctx.expr());
        String name = ctx.IDENT().getText();
        int idx = variablesValue.get(name);
        if (currentFunction == null) emit(OpCode.gstore, idx);
        else                         emit(OpCode.lstore, idx);
        return null;
    }

    @Override
    public Void visitWhileStat(TugaParser.WhileStatContext ctx) {
        int start = code.size();
        visit(ctx.expr());
        emit(OpCode.jumpf, 0);
        int jf = code.size() - 1;
        visit(ctx.stat());
        emit(OpCode.jump, start);
        ((Instruction1Arg) code.get(jf)).setArg(code.size());
        return null;
    }

    @Override
    public Void visitIfStat(TugaParser.IfStatContext ctx) {
        visit(ctx.expr());
        emit(OpCode.jumpf, 0);
        int jf = code.size() - 1;
        visit(ctx.stat(0));
        if (ctx.stat().size() == 2) {
            emit(OpCode.jump, 0);
            int j = code.size() - 1;
            ((Instruction1Arg) code.get(jf)).setArg(code.size());
            visit(ctx.stat(1));
            ((Instruction1Arg) code.get(j)).setArg(code.size());
        } else {
            ((Instruction1Arg) code.get(jf)).setArg(code.size());
        }
        return null;
    }

    @Override public Void visitEmptyStat(TugaParser.EmptyStatContext ctx) { return null; }

    @Override public Void visitIdentExpr(TugaParser.IdentExprContext ctx) {
        int idx = variablesValue.get(ctx.IDENT().getText());
        if (currentFunction == null) emit(OpCode.gload, idx);
        else                         emit(OpCode.lload, idx);
        return null;
    }

    @Override
    public Void visitFunctionCallExpr(TugaParser.FunctionCallExprContext ctx) {
        String name = ctx.IDENT().getText();
        Symbol sym = globalScope.resolve(name);
        if (!(sym instanceof FunctionSymbol function)) {
            throw new RuntimeException("'" + name + "' não é FunctionSymbol");
        }

        List<Symbol> formals = function.get_arguments();
        int nFormals = formals.size();
        int nArgs = ctx.expr().size();

        for (int i = 0; i < nArgs; i++) {
            TugaParser.ExprContext argCtx = ctx.expr(i);
            visit(argCtx);

            Type argType = tipos.get(argCtx);

            VariableSymbol vs = (VariableSymbol) formals.get(i);
            Type paramType = mapSymType(vs.type);

            if (paramType == Type.REAL && argType == Type.INT) {
                emit(OpCode.itod);
            }
        }

        if (functionAddresses.containsKey(name)) {
            int addr = functionAddresses.get(name);
            emit(OpCode.call, addr);
        } else {
            emit(OpCode.call, 0);
            int callInstrIndex = code.size() - 1;
            pendingPatches.computeIfAbsent(name, k -> new ArrayList<>()).add(callInstrIndex);
        }
        return null;
    }

    /** Mapeamento auxiliar do Symbol.Type → nosso enum Type **/
    private Type mapSymType(Symbol.Type st) {
        return switch (st) {
            case tINT     -> Type.INT;
            case tFLOAT   -> Type.REAL;
            case tBOOLEAN -> Type.BOOL;
            case tSTRING  -> Type.STRING;
            default       -> Type.NULO;
        };
    }

    @Override public Void visitIntExpr(TugaParser.IntExprContext ctx) {
        emit(OpCode.iconst, Integer.parseInt(ctx.INT().getText()));
        return null;
    }
    @Override public Void visitDoubleExpr(TugaParser.DoubleExprContext ctx) {
        int i = constPool.add(Double.parseDouble(ctx.DOUBLE().getText()));
        emit(OpCode.dconst, i); return null;
    }
    @Override public Void visitStringExpr(TugaParser.StringExprContext ctx) {
        String s = ctx.STRING().getText();
        s = s.substring(1, s.length() - 1);
        emit(OpCode.sconst, constPool.add(s));
        return null;
    }
    @Override public Void visitBooleanExpr(TugaParser.BooleanExprContext ctx) {
        emit(ctx.BOOLEAN().getText().equals("verdadeiro") ? OpCode.tconst : OpCode.fconst);
        return null;
    }
    @Override public Void visitUnaryExpr(TugaParser.UnaryExprContext ctx) {
        visit(ctx.expr());
        Type t = tipos.get(ctx.expr());
        if (ctx.op.getText().equals("-")) emit(t==Type.INT?OpCode.iuminus:OpCode.duminus);
        else                                 emit(OpCode.not);
        return null;
    }
    @Override public Void visitMulDivExpr(TugaParser.MulDivExprContext ctx) {
        Type leftType = tipos.get(ctx.expr(0));
        Type rightType = tipos.get(ctx.expr(1));
        String op = ctx.op.getText();

        visit(ctx.expr(0));
        if (leftType == Type.INT && rightType == Type.REAL) emit(OpCode.itod);

        visit(ctx.expr(1));
        if (rightType == Type.INT && leftType == Type.REAL) emit(OpCode.itod);

        if (op.equals("*")) {
            if (leftType == Type.REAL || rightType == Type.REAL) {
                emit(OpCode.dmult);
            } else if (leftType == Type.INT && rightType == Type.INT) {
                emit(OpCode.imult);
            }
        } else if (op.equals("/")) {
            if (leftType == Type.REAL || rightType == Type.REAL) {
                emit(OpCode.ddiv);
            } else if (leftType == Type.INT && rightType == Type.INT) {
                emit(OpCode.idiv);
            }
        } else if (op.equals("%")) {
            emit(OpCode.imod);
        }
        return null;
    }
    @Override public Void visitAddSubExpr(TugaParser.AddSubExprContext ctx) {
        Type leftType = tipos.get(ctx.expr(0));
        Type rightType = tipos.get(ctx.expr(1));
        String op = ctx.op.getText();

        if (op.equals("+")) {
            if (leftType == Type.STRING || rightType == Type.STRING) {
                visit(ctx.expr(0));
                if (leftType == Type.INT) emit(OpCode.itos);
                else if (leftType == Type.REAL) emit(OpCode.dtos);
                else if (leftType == Type.BOOL) emit(OpCode.btos);

                visit(ctx.expr(1));
                if (rightType == Type.INT) emit(OpCode.itos);
                else if (rightType == Type.REAL) emit(OpCode.dtos);
                else if (rightType == Type.BOOL) emit(OpCode.btos);

                emit(OpCode.sconcat);

            } else if (leftType == Type.REAL || rightType == Type.REAL) {
                visit(ctx.expr(0));
                if (leftType == Type.INT) emit(OpCode.itod);

                visit(ctx.expr(1));
                if (rightType == Type.INT) emit(OpCode.itod);

                emit(OpCode.dadd);
            } else if (leftType == Type.INT && rightType == Type.INT) {
                visit(ctx.expr(0));
                visit(ctx.expr(1));
                emit(OpCode.iadd);
            }
        } else if (op.equals("-")) {
            if (leftType == Type.REAL || rightType == Type.REAL) {
                visit(ctx.expr(0));
                if (leftType == Type.INT) emit(OpCode.itod);

                visit(ctx.expr(1));
                if (rightType == Type.INT) emit(OpCode.itod);
                emit(OpCode.dsub);
            } else if (leftType == Type.INT && rightType == Type.INT) {
                visit(ctx.expr(0));
                visit(ctx.expr(1));
                emit(OpCode.isub);
            }
        }
        return null;
    }
    @Override public Void visitRelationalExpr(TugaParser.RelationalExprContext ctx) {
        String op = ctx.op.getText();
        if (op.equals("<")||op.equals("<=")){
            visit(ctx.expr(0)); visit(ctx.expr(1));
            emit(op.equals("<")?OpCode.ilt:OpCode.ileq);
        } else {
            visit(ctx.expr(1)); visit(ctx.expr(0));
            emit(op.equals(">")?OpCode.ilt:OpCode.ileq);
        }
        return null;
    }
    @Override public Void visitEqualityExpr(TugaParser.EqualityExprContext ctx) {
        visit(ctx.expr(0)); visit(ctx.expr(1));
        emit(ctx.op.getText().equals("igual")?OpCode.ieq:OpCode.ineq);
        return null;
    }
    @Override public Void visitAndExpr(TugaParser.AndExprContext ctx) {
        visit(ctx.expr(0)); visit(ctx.expr(1)); emit(OpCode.and); return null;
    }
    @Override public Void visitOrExpr(TugaParser.OrExprContext ctx) {
        visit(ctx.expr(0)); visit(ctx.expr(1)); emit(OpCode.or); return null;
    }

    public void dumpCode() {
        for (int i=0; i<code.size(); i++) System.out.println(i+": " + code.get(i));
    }

    public void saveBytecodes(String filename) throws IOException {
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream(filename))) {
            constPool.writeTo(dout);
            for (Instruction inst : code) inst.writeTo(dout);
        }
    }
}
