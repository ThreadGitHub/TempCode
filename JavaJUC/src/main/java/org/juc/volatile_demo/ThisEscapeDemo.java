package org.juc.volatile_demo;

/**
 * this逸出问题
 * @author thread
 * @date 2023/10/13 12:01
 */
public class ThisEscapeDemo {
    private int num = 9;

    public ThisEscapeDemo() {
        new Thread(()-> {
            System.out.println(this.num);
        }).start();
    }

    public static void main(String[] args) {
        new ThisEscapeDemo();
        new ThisEscapeDemo();
        new ThisEscapeDemo();
    }
}
