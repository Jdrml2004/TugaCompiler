package VM.Instruction;

import VM.OpCode;
import java.io.DataOutputStream;
import java.io.IOException;

public class Instruction1Arg extends Instruction {
    private int arg;

    public Instruction1Arg(OpCode opcode, int arg) {
        super(opcode);
        this.arg = arg;
    }

    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
    }

    @Override
    public void writeTo(DataOutputStream dout) throws IOException {
        dout.writeByte(getOpCode().ordinal());
        dout.writeInt(getArg());
    }

    @Override
    public String toString() {
        return super.toString() + " " + arg;
    }
}