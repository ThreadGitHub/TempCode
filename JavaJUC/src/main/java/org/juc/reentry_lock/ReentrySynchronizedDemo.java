package org.juc.reentry_lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Synchronized 可重入锁示例
 * @author thread
 * @date 2023/9/28 14:51
 */
public class ReentrySynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrySynchronizedDemo reentrySynchronizedDemo = new ReentrySynchronizedDemo();

        // 开启两个线程
        CompletableFuture.runAsync(reentrySynchronizedDemo::methodA);
        CompletableFuture.runAsync(reentrySynchronizedDemo::methodA);

        // 阻塞main
        Thread.currentThread().join();
    }

    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + ": methodA");
        methodB();
    }

    private synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + ": methodB sleep start");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + ": methodB");
        System.out.println(Thread.currentThread().getName() + ": methodB sleep stop");
    }
}
