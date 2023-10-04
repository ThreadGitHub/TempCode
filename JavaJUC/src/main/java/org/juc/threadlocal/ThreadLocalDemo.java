package org.juc.threadlocal;

import java.util.Random;

/**
 * ThreadLocal 简单使用
 * @author thread
 * @date 2023/10/4 15:05
 */
public class ThreadLocalDemo {
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()-> 0);

    public void add() {
        threadLocal.set(threadLocal.get() + 1);
    }

    public void print() {
        try {
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        } finally {
            threadLocal.remove();
        }
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(()-> {
                int num = new Random().nextInt(7) + 1;
                for (int j = 0; j < num; j++) {
                    demo.add();
                }
                demo.print();
            }).start();
        }
    }
}
