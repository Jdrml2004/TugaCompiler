import TypeChecking.Type;
import TypeChecking.DefPhase;
import TypeChecking.RefPhase;
import TypeChecking.TypeCheckingVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

import Tuga.TugaLexer;
import Tuga.TugaParser;
import CodeGenerator.CodeGen;
import CodeGenerator.ConstantPool;
import VM.TugaVM;

public class TugaCompileAndRun {
    public static void main(String[] args) throws IOException {
        boolean showLexerErrors = false;
        boolean showParserErrors = false;

        String outputFilename = "bytecodes.bc";
        CharStream input;

        if (args.length == 0) {
            StringBuilder sourceBuilder = new StringBuilder();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    sourceBuilder.append(line).append("\n");
                }
            }
            scanner.close();
            input = CharStreams.fromString(sourceBuilder.toString().trim());
        } else {
            String inputFilename = args[0];
            if (!inputFilename.endsWith(".tuga")) {
                System.err.println("input file must have a '.tuga' extension");
                System.exit(1);
            }
            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                    case "-showLexerErrors": showLexerErrors = true; break;
                    case "-showParserErrors": showParserErrors = true; break;
                }
            }
            try (InputStream is = new FileInputStream(inputFilename)) {
                input = CharStreams.fromStream(is);
            } catch (IOException e) {
                System.err.println("Error reading input file: " + e.getMessage());
                System.exit(1);
                return;
            }
        }

        MyErrorListener errorListener = new MyErrorListener(showLexerErrors, showParserErrors);
        TugaLexer lexer = new TugaLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TugaParser parser = new TugaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);


        ParseTree tree = parser.prog();
        DefPhase def = new DefPhase();
        def.visit(tree);

        RefPhase ref = new RefPhase(def.global, def.scopes);
        ref.visit(tree);

        TypeCheckingVisitor typeChecker = new TypeCheckingVisitor(def.global, def.scopes);
        typeChecker.visit(tree);
        Map<ParseTree, Type> tipos = typeChecker.getNodeTypes();
        if (typeChecker.getTypeCheckingErrors()) {
            System.exit(0);
        }

        ConstantPool constPool = new ConstantPool();
        CodeGen codeGen = new CodeGen(tipos, constPool, def.scopes, def.global);
        codeGen.visit(tree);
        codeGen.saveBytecodes(outputFilename);

        System.out.println("*** Constant pool ***");
        ConstantPool.printPool();
        System.out.println("*** Instructions ***");
        codeGen.dumpCode();

        byte[] bytecodes = loadBytecodes(outputFilename);
        System.out.println("*** VM output ***");
        new TugaVM(bytecodes).run();
    }

    private static byte[] loadBytecodes(String filename) throws IOException {
        File file = new File(filename);
        byte[] bytecodes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytecodes);
        }
        return bytecodes;
    }
}
