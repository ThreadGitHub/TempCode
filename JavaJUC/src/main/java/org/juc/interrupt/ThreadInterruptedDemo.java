package org.juc.interrupt;

/**
 * Thread.interrupted 静态方法demo
 * 做了两件事：1.返回中断标志位的值 2.标志位重新修改为false
 * @author thread
 * @date 2023/9/29 23:07
 */
public class ThreadInterruptedDemo {
    public static void main(String[] args) {
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());

        Thread.currentThread().interrupt();

        // 返回true 并设置为false
        System.out.println(Thread.interrupted());
        // 返回false值
        System.out.println(Thread.interrupted());
    }
}
