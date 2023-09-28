package org.juc.dead_lock;

import java.util.concurrent.TimeUnit;

/**
 * 线程死锁示例
 * @author thread
 * @date 2023/9/28 15:06
 */
public class DeadLockDemo {

    private static Object lockA = new Object();
    private static Object lockB = new Object();

    public static void main(String[] args) {
        new Thread(()-> {
            synchronized (lockA) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (lockB) {
                    System.out.println("lockB");
                }
            }
        }, "Thread-A").start();

        new Thread(()-> {
            synchronized (lockB) {
                synchronized (lockA) {
                    System.out.println("lockB");
                }
            }
        }, "Thread-B").start();
    }
}
