package org.juc.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * 测试MyReentrantLock
 * @author thread
 * @date 2023/10/15 21:48
 */
public class MyReentrantLockTest {
    static int num = 0;

    static MyReentrantLock reentrantLock = new MyReentrantLock();

    public static void add() {
        reentrantLock.lock();
        try {
            num += 1;
//            System.out.println(Thread.currentThread().getName() + ": " + num);
//            TimeUnit.MILLISECONDS.sleep(10);
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        new Thread(()-> {
            int num = 0;
            for (int i = 0; i < 10000; i++) {
                add();
            }
            countDownLatch.countDown();
        }, "thread-A").start();

        new Thread(()-> {
            int num = 0;
            for (int i = 0; i < 10000; i++) {
                add();
            }
            countDownLatch.countDown();
        }, "thread-B").start();

        new Thread(()-> {
            int num = 0;
            for (int i = 0; i < 10000; i++) {
                add();
            }
            countDownLatch.countDown();
        }, "thread-C").start();

        new Thread(()-> {
            int num = 0;
            for (int i = 0; i < 10000; i++) {
                add();
            }
            countDownLatch.countDown();
        }, "thread-D").start();

        new Thread(()-> {
            int num = 0;
            for (int i = 0; i < 10000; i++) {
                add();
            }
            countDownLatch.countDown();
        }, "thread-E").start();

        new Thread(()-> {
            int num = 0;
            for (int i = 0; i < 10000; i++) {
                add();
            }
            countDownLatch.countDown();
        }, "thread-F").start();

        countDownLatch.await();
        System.out.println(num);
    }
}
