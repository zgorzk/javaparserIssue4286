import java.util.ArrayList;

public class B {

    public void methodWithForEachInB() {
        C c = new C();
        new ArrayList<>().forEach(el -> c.methodWithForEachInC());
    }
}

