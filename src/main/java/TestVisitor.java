import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;

public class TestVisitor extends VoidVisitorAdapter<Void> {

    @Override
    public void visit(MethodDeclaration node, Void arg) {
        System.out.println(node.toString());
        super.visit(node, arg);
    }
    @Override
    public void visit(MethodCallExpr node, Void arg) {
        try {
            var symbol = node.resolve();
            if (symbol instanceof JavaParserMethodDeclaration methodDeclaration) {
                visit(methodDeclaration.getWrappedNode(), arg);
//                methodDeclaration.getWrappedNode().accept(new TestVisitor(), arg);  //also not working
            } else {
                super.visit(node, arg);
            }
        } catch (RuntimeException e) {
            var solver = node.getSymbolResolver();
            e.printStackTrace();
        }
    }
}
