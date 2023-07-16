package thread.jvm.stack;

import java.util.Stack;

public class StackTest {
    public void methodA () {
        int a = 10;
        int b = 20;
        int sum = a + b;
    }

    public void methodB() {
        int num = 10;
        {
            int num2 = num + 1;
        }
        int sum = num + 100;
    }

    public static void main(String[] args) {
        new StackTest().methodA();

        Stack<String> stack = new Stack<>();
        stack.add("1");
        stack.add("2");
        stack.add("3");
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
