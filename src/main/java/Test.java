
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        String SRC_PATH = "src/main/java";
        String FILE_PATH = "src/main/java/A.java";

        CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        typeSolver.add(new JavaParserTypeSolver(new File(SRC_PATH)));
        typeSolver.add(new ReflectionTypeSolver(false));

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        ParserConfiguration pc = new ParserConfiguration().setSymbolResolver(symbolSolver);
        StaticJavaParser.setConfiguration(pc);

        TestVisitor testVisitor = new TestVisitor();

        CompilationUnit c = StaticJavaParser.parse(new File(FILE_PATH));
        c.accept(testVisitor,null);
    }
}
