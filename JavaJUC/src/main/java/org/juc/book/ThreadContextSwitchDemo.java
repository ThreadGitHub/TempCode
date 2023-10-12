package org.juc.book;

/**
 * 线程上下文切换demo
 * @author thread
 * @date 2023/10/10 23:03
 */
public class ThreadContextSwitchDemo {
    // M2芯片 10亿的情况线程并发才比串行速度要快 在不加睡眠的情况下
    public static final long COUNT = 10000 * 100000;

    public static void main(String[] args) throws InterruptedException {
        thread();
        main();
        Thread.currentThread().join();
    }

    public static void thread() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            long a = 0;
            for (int i = 0; i < COUNT; i++) {
                a++;
            }
        });

        long b = COUNT;
        for (int i = 0; i < COUNT; i++) {
            b--;
        }

        thread.join();
        long stop = System.currentTimeMillis();
        long time = stop - start;
        System.out.println(time);
    }

    public static void main() {
        long start = System.currentTimeMillis();
        long a = 0;
        for (int i = 0; i < COUNT; i++) {
            a++;
        }

        long b = COUNT;
        for (int i = 0; i < COUNT; i++) {
            b--;
        }
        long stop = System.currentTimeMillis();
        long time = stop - start;
        System.out.println(time);
    }
}
