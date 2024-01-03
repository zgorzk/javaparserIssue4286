import java.util.ArrayList;

public class A {

    public void methodWithForEachInA() {
        B b = new B();
        new ArrayList<>().forEach(el -> b.methodWithForEachInB());
    }
}
