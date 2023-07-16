package thread.jvm.stack;

public final class DynamicsLinkeTest {
    public void methodA() {
        methodB();
    }

    public void methodB() {
        int a = 10;
        methodC();
    }

    public void methodC() {
        System.out.println("MethodC.");
    }

    public DynamicsLinkeTest() {
        super();
    }

    public DynamicsLinkeTest(int num) {
        this();
    }

    public static void main(String[] args) {
        new DynamicsLinkeTest().methodA();
    }
}
