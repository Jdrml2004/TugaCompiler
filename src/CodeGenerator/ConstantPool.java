package CodeGenerator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConstantPool {

    private static ArrayList<Object> constants = new ArrayList<>();

    public static int add(Object obj) {
        int index = constants.indexOf(obj);
        if (index == -1) {
            index = constants.size();
            constants.add(obj);
        }
        return index;
    }

    public static Object getConstant(int index) {
        if (index >= 0 && index < constants.size()) {
            return constants.get(index);
        }
        return null;
    }

    public static void printPool() {
        for (int i = 0; i < constants.size(); i++) {
            Object constant = constants.get(i);
            if (constant instanceof String) {
                System.out.println(i + ": \"" + constant + "\"");
            } else {
                System.out.println(i + ": " + constant);
            }
        }
    }

    public static int size() {
        return constants.size();
    }

    public static void writeTo(DataOutputStream dout) throws IOException {
        dout.writeInt(constants.size());
        for (Object constant : constants) {
            if (constant instanceof Double) {
                dout.writeByte(0x01);
                dout.writeDouble((Double) constant);
            } else if (constant instanceof String) {
                dout.writeByte(0x03);
                String str = (String) constant;
                dout.writeInt(str.length());
                byte[] bytes = str.getBytes("UTF-16BE");
                dout.write(bytes);
            }
        }
    }
}