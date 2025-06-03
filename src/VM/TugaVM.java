package VM;

import CodeGenerator.ConstantPool;
import VM.Instruction.*;
import java.io.*;
import java.util.*;

public class TugaVM {
    private final byte[] bytecodes;
    private Instruction[] code;
    private int IP;
    private int FP;
    private final Stack<Object> stack = new Stack<>();
    private final Stack<Integer> callStack = new Stack<>();
    private final Stack<Integer> fpStack = new Stack<>();
    private ConstantPool constantPool;
    private final List<Object> globalMemory = new ArrayList<>();

    public TugaVM(byte[] bytecodes) {
        this.bytecodes = bytecodes;
        decode(bytecodes);
        this.IP = 0;
        this.FP = 0;
    }

    private void decode(byte[] bytecodes) {
        List<Instruction> insts = new ArrayList<>();
        try (DataInputStream din = new DataInputStream(new ByteArrayInputStream(bytecodes))) {
            int numConstants = din.readInt();
            constantPool = new ConstantPool();
            for (int i = 0; i < numConstants; i++) {
                byte type = din.readByte();
                if (type == 0x01) {
                    double d = din.readDouble();
                    ConstantPool.add(d);
                } else if (type == 0x03) {
                    int len = din.readInt();
                    byte[] strBytes = new byte[len * 2];
                    din.readFully(strBytes);
                    String s = new String(strBytes, "UTF-16BE");
                    ConstantPool.add(s);
                } else {
                    System.err.println("Unknown constant type: " + type);
                    System.exit(1);
                }
            }
            while (din.available() > 0) {
                byte b = din.readByte();
                OpCode opc = OpCode.convert(b);
                if (opc.nArgs() == 0) {
                    insts.add(new Instruction(opc));
                } else {
                    int arg = din.readInt();
                    insts.add(new Instruction1Arg(opc, arg));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        code = insts.toArray(new Instruction[0]);
    }

    private void exec_iconst(int v) {
        stack.push(v);
    }

    private void exec_dconst(int index) {
        Object c = constantPool.getConstant(index);
        if (c instanceof Double) {
            stack.push((Double) c);
        }
    }

    private void exec_sconst(int index) {
        Object c = constantPool.getConstant(index);
        if (c instanceof String) {
            stack.push(c);
        }
    }

    private void exec_iprint() {
        Object o = stack.pop();
        if (o instanceof Integer)
            System.out.println(o);
        else if (o == null)
            System.out.println("NULO");
    }

    private void exec_iuminus() {
        Object o = stack.pop();
        if (o instanceof Integer)
            stack.push(-((Integer) o));
        else if (o == null) // Handle NULO
            runtime_error("tentativa de acesso a valor NULO");
    }

    private void exec_iadd() {
        // Pop right then left
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Integer && rObj instanceof Integer) {
            int r = (Integer) rObj;
            int l = (Integer) lObj;
            stack.push(l + r);
        }
    }

    private void exec_isub() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Integer && rObj instanceof Integer) {
            int r = (Integer) rObj;
            int l = (Integer) lObj;
            stack.push(l - r);
        }
    }

    private void exec_imult() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Integer && rObj instanceof Integer) {
            int r = (Integer) rObj;
            int l = (Integer) lObj;
            stack.push(l * r);
        }
    }

    private void exec_idiv() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Integer && rObj instanceof Integer) {
            int r = (Integer) rObj;
            int l = (Integer) lObj;
            if (r != 0)
                stack.push(l / r);
            else
                runtime_error("Division by zero");
        }
    }

    private void exec_imod() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Integer && rObj instanceof Integer) {
            int r = (Integer) rObj;
            int l = (Integer) lObj;
            if (r != 0)
                stack.push(l % r);
            else
                runtime_error("Modulus division by zero");
        }
    }

    private void exec_ieq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();

        if (aObj == null || bObj == null) {
            stack.push(aObj == bObj);
        } else if (aObj instanceof Integer && bObj instanceof Integer) {
            int b = (Integer) bObj;
            int a = (Integer) aObj;
            stack.push(a==b);
        } else if (aObj instanceof Boolean && bObj instanceof Boolean) {
            Boolean b = (Boolean) bObj;
            Boolean a = (Boolean) aObj;
            stack.push(a==b);
        }
    }

    private void exec_ineq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();

        if (aObj == null || bObj == null) {
            stack.push(!(aObj == bObj));
        } else if (aObj instanceof Integer && bObj instanceof Integer) {
            int b = (Integer) bObj;
            int a = (Integer) aObj;
            stack.push(a!=b);
        } else if (aObj instanceof Boolean && bObj instanceof Boolean) {
            Boolean b = (Boolean) bObj;
            Boolean a = (Boolean) aObj;
            stack.push(a!=b);
        }
    }

    private void exec_ilt() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (bObj == null || aObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (aObj instanceof Integer && bObj instanceof Integer) {
            int b = (Integer) bObj;
            int a = (Integer) aObj;
            stack.push(a < b);
        }
    }

    private void exec_ileq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (bObj == null || aObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (aObj instanceof Integer && bObj instanceof Integer) {
            int b = (Integer) bObj;
            int a = (Integer) aObj;
            stack.push(a <= b);
        }
    }

    private void exec_itod() {
        Object o = stack.pop();
        if (o == null)
            runtime_error("tentativa de acesso a valor NULO");

        if (o instanceof Integer) {
            int i = (Integer) o;
            stack.push((double) i);
        }
    }

    private void exec_itos() {
        Object o = stack.pop();
        if (o == null)
            runtime_error("tentativa de acesso a valor NULO");

        if (o instanceof Integer) {
            int i = (Integer) o;
            stack.push(Integer.toString(i));
        }
    }

    private void exec_dprint() {
        Object o = stack.pop();
        if (o instanceof Double)
            System.out.println(o);
        else if (o == null)
            System.out.println("NULO");
    }

    private void exec_duminus() {
        Object o = stack.pop();
        if (o == null) // Handle NULO
            runtime_error("tentativa de acesso a valor NULO");
        if (o instanceof Double)
            stack.push(-((Double) o));
    }

    private void exec_dadd() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Double && rObj instanceof Double) {
            double r = (Double) rObj;
            double l = (Double) lObj;
            stack.push(l + r);
        }
    }

    private void exec_dsub() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Double && rObj instanceof Double) {
            double r = (Double) rObj;
            double l = (Double) lObj;
            stack.push(l - r);
        }
    }

    private void exec_dmult() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Double && rObj instanceof Double) {
            double r = (Double) rObj;
            double l = (Double) lObj;
            stack.push(l * r);
        }
    }

    private void exec_ddiv() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Double && rObj instanceof Double) {
            double r = (Double) rObj;
            double l = (Double) lObj;
            if (r != 0)
                stack.push(l / r);
            else
                runtime_error("Division by zero (double)");
        }
    }

    private void exec_deq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            stack.push(aObj == bObj);
        } else if (aObj instanceof Double && bObj instanceof Double) {
            double b = (Double) bObj;
            double a = (Double) aObj;
            stack.push(a==b);
        }
    }

    private void exec_dneq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            stack.push(!(aObj == bObj));
        } else if (aObj instanceof Double && bObj instanceof Double) {
            double b = (Double) bObj;
            double a = (Double) aObj;
            stack.push(a!=b);
        }
    }

    private void exec_dlt() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (aObj instanceof Double && bObj instanceof Double) {
            double b = (Double) bObj;
            double a = (Double) aObj;
            stack.push(a<b);
        }
    }

    private void exec_dleq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (aObj instanceof Double && bObj instanceof Double) {
            double b = (Double) bObj;
            double a = (Double) aObj;
            stack.push(a<=b);
        }
    }

    private void exec_dtos() {
        Object o = stack.pop();
        if (o == null) // Handle NULO
            runtime_error("tentativa de acesso a valor NULO");
        if (o instanceof Double) {
            double d = (Double) o;
            stack.push(Double.toString(d));
        }
    }

    private void exec_sprint() {
        Object o = stack.pop();
        if (o instanceof String)
            System.out.println(o);
        else if (o == null) // Handle NULO
            System.out.println("NULO");

    }

    private void exec_sconcat() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            runtime_error("tentativa de acesso a valor NULO durante concatenação");
        }
        if (aObj instanceof String && bObj instanceof String) {
            String b = (String) bObj;
            String a = (String) aObj;
            String result = ((String) a) + ((String) b);
            stack.push(result);
        } else {
            String aStr = (aObj != null) ? aObj.toString() : "NULO";
            String bStr = (bObj != null) ? bObj.toString() : "NULO";
            stack.push(aStr + bStr);
        }
    }

    private void exec_seq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();

        if (aObj == null || bObj == null) {
            stack.push(aObj == bObj);
        } else if (aObj instanceof String && bObj instanceof String) {
            String b = (String) bObj;
            String a = (String) aObj;
            stack.push(a.equals(b));
        }
    }

    private void exec_sneq() {
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            stack.push(!(aObj == bObj));
        } else if (aObj instanceof String && bObj instanceof String) {
            String b = (String) bObj;
            String a = (String) aObj;
            stack.push(!(a.equals(b)));
        }
    }

    private void exec_tconst() {
        stack.push(true);
    }

    private void exec_fconst() {
        stack.push(false);
    }

    private void exec_bprint() {
        Object o = stack.pop();
        if (o instanceof Boolean)
            System.out.println(((Boolean) o) ? "verdadeiro" : "falso");
        else if (o == null)
            System.out.println("NULO");
    }

    private void exec_beq(){
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            stack.push(aObj == bObj);
        } else if (aObj instanceof Boolean && bObj instanceof Boolean) {
            Boolean b = (Boolean) bObj;
            Boolean a = (Boolean) aObj;
            stack.push(a==b);
        }
    }

    private void exec_bneq(){
        Object bObj = stack.pop();
        Object aObj = stack.pop();
        if (aObj == null || bObj == null) {
            stack.push(!(aObj == bObj));
        } else if (aObj instanceof Boolean && bObj instanceof Boolean) {
            Boolean b = (Boolean) bObj;
            Boolean a = (Boolean) aObj;
            stack.push(a!=b);
        }
    }

    // Operações booleanas:
    private void exec_and() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Boolean && rObj instanceof Boolean) {
            boolean r = (Boolean) rObj;
            boolean l = (Boolean) lObj;
            stack.push(l && r);
        }
    }

    private void exec_or() {
        Object rObj = stack.pop();
        Object lObj = stack.pop();
        if (lObj == null || rObj == null) {
            runtime_error("tentativa de acesso a valor NULO");
        }
        if (lObj instanceof Boolean && rObj instanceof Boolean) {
            boolean r = (Boolean) rObj;
            boolean l = (Boolean) lObj;
            stack.push(l || r);
        }
    }

    private void exec_not() {
        Object o = stack.pop();
        if (o == null)
            runtime_error("tentativa de acesso a valor NULO");

        if (o instanceof Boolean) {
            boolean b = (Boolean) o;
            stack.push(!b);
        }
    }

    private void exec_btos() {
        Object o = stack.pop();
        if (o == null)
            runtime_error("tentativa de acesso a valor NULO");
        if (o instanceof Boolean) {
            boolean b = (Boolean) o;
            stack.push(b ? "verdadeiro" : "falso");
        }
    }

    // Instruções novas (SVM-mini 2)

    // opcode 41
    private void exec_jump(int addr) {
        IP = addr;
    }

    // opcode 42
    private void exec_jumpf(int addr) {
        Object value = stack.pop();
        if (value == null) {
            runtime_error("tentativa de acesso a valor NULO em condição de salto");
        }
        if (value instanceof Boolean) {
            if (!((Boolean) value)) {
                IP = addr;
            }
        }
    }

    // opcode 43
    private void exec_galloc(int n) {
        if (n >= 0) {
            for (int i = 0; i < n; i++) {
                globalMemory.add(null);
            }
        }
    }

    // opcode 44
    private void exec_gload(int addr) {
        if (addr >= 0 && addr < globalMemory.size()) {
            Object value = globalMemory.get(addr);
            stack.push(value);
        }
    }

    // opcode 45
    private void exec_gstore(int addr) {
        if (addr >= 0 && addr < globalMemory.size()) {
            if (!stack.isEmpty()) {
                globalMemory.set(addr, stack.pop());
            }
        }
    }


    private void exec_lalloc(int n)   { for(int i=0;i<n;i++) stack.push(null); }
    private void exec_lload(int off)  { stack.push(stack.get(FP+off)); }
    private void exec_lstore(int off) { stack.set(FP+off, stack.pop()); }

    private void exec_pop(int n) {
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) runtime_error("pop em pilha vazia");
            stack.pop();
        }
    }

    private void exec_call(int addr) {
        callStack.push(IP);
        fpStack.push(FP);
        stack.push(null);
        stack.push(null);
        FP = stack.size() - 2;
        IP = addr;
    }

    private void exec_retval(int nArgs) {
        Object retval = stack.pop();
        for(int i=0;i<nArgs;i++) stack.pop();
        stack.pop();
        stack.pop();
        FP = fpStack.pop();
        IP = callStack.pop();
        stack.push(retval);
    }

    private void exec_ret(int nArgs) {
        for(int i=0;i<nArgs;i++) stack.pop();
        stack.pop();
        stack.pop();
        FP = fpStack.pop();
        IP = callStack.pop();
    }

    private void exec_halt() { IP = code.length; }

    private void runtime_error(String msg) {
        System.err.println("erro de runtime: " + msg);
        System.exit(1);
    }

    private Instruction lastInstr() {
        return code[code.length-1];
    }

    private void exec_inst(Instruction inst) {
        OpCode opc = inst.getOpCode();
        switch (opc) {
            case iuminus: case not: case itod: case itos: case dtos: case btos:
            case iprint: case dprint: case sprint: case bprint:
            case iadd: case isub: case imult: case idiv: case imod:
            case dadd: case dsub: case dmult: case ddiv:
            case and: case or:
            case ieq: case ineq: case ilt: case ileq:
            case deq: case dneq: case dlt: case dleq:
            case seq: case sneq: case beq: case bneq:
            case sconcat:
            case gstore:
            case lalloc: case lload: case lstore:
            case pop:
            case call: case retval: case ret:
        }


        switch (opc) {
            case iconst:
                exec_iconst(((Instruction1Arg) inst).getArg());
                break;
            case dconst:
                exec_dconst(((Instruction1Arg) inst).getArg());
                break;
            case sconst:
                exec_sconst(((Instruction1Arg) inst).getArg());
                break;
            case tconst:
                exec_tconst();
                break;
            case ileq:
                exec_ileq();
                break;
            case deq:
                exec_deq();
                break;
            case dneq:
                exec_dneq();
                break;
            case dlt:
                exec_dlt();
                break;
            case dleq:
                exec_dleq();
                break;
            case sconcat:
                exec_sconcat();
                break;
            case seq:
                exec_seq();
                break;
            case sneq:
                exec_sneq();
                break;
            case beq:
                exec_beq();
                break;
            case bneq:
                exec_bneq();
                break;

            case fconst:
                exec_fconst();
                break;
            case iuminus:
                exec_iuminus();
                break;
            case not:
                exec_not();
                break;
            case iadd:
                exec_iadd();
                break;
            case isub:
                exec_isub();
                break;
            case imult:
                exec_imult();
                break;
            case ieq:
                exec_ieq();
                break;
            case ineq:
                exec_ineq();
                break;
            case idiv:
                exec_idiv();
                break;
            case imod:
                exec_imod();
                break;
            case duminus:
                exec_duminus();
                break;
            case dadd:
                exec_dadd();
                break;
            case dsub:
                exec_dsub();
                break;
            case dmult:
                exec_dmult();
                break;
            case ddiv:
                exec_ddiv();
                break;
            case and:
                exec_and();
                break;
            case or:
                exec_or();
                break;
            case iprint:
                exec_iprint();
                break;
            case dprint:
                exec_dprint();
                break;
            case ilt:
                exec_ilt();
                break;
            case sprint:
                exec_sprint();
                break;
            case bprint:
                exec_bprint();
                break;
            case itod:
                exec_itod();
                break;
            case itos:
                exec_itos();
                break;
            case dtos:
                exec_dtos();
                break;
            case btos:
                exec_btos();
                break;
            case jump:
                exec_jump(((Instruction1Arg) inst).getArg());
                break;
            case jumpf:
                exec_jumpf(((Instruction1Arg) inst).getArg());
                break;
            case galloc:
                exec_galloc(((Instruction1Arg) inst).getArg());
                break;
            case gload:
                exec_gload(((Instruction1Arg) inst).getArg());
                break;
            case gstore:
                exec_gstore(((Instruction1Arg) inst).getArg());
                break;
            case lalloc:
                exec_lalloc(((Instruction1Arg) inst).getArg());
                break;
            case lload:
                exec_lload(((Instruction1Arg) inst).getArg());
                break;
            case lstore:
                exec_lstore(((Instruction1Arg) inst).getArg());
                break;
            case pop:
                exec_pop(((Instruction1Arg) inst).getArg());
                break;
            case call:
                exec_call(((Instruction1Arg) inst).getArg());
                break;
            case ret:
                exec_ret(((Instruction1Arg) inst).getArg());
                break;
            case retval:
                exec_retval(((Instruction1Arg) inst).getArg());
                break;
            case halt:
                exec_halt();
                break;
            default:
                runtime_error("Unknown opcode: " + opc + " at IP " + (IP - 1));
        }
    }

    public void run() {
        while (IP < code.length) {
            Instruction inst = code[IP++];
            exec_inst(inst);
        }
    }
}
