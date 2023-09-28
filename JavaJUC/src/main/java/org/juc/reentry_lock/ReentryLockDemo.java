package org.juc.reentry_lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁 可重入锁示例
 * @author thread
 * @date 2023/9/28 14:53
 */
public class ReentryLockDemo {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ReentryLockDemo reentryLockDemo = new ReentryLockDemo();

        // 开启两个线程
        CompletableFuture.runAsync(reentryLockDemo::methodA);
        CompletableFuture.runAsync(reentryLockDemo::methodA);

        // 阻塞main
        Thread.currentThread().join();
    }

    public synchronized void methodA() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": methodA");
            methodB();
        } finally {
            lock.unlock();
        }
    }

    private synchronized void methodB() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": methodB sleep start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": methodB");
            System.out.println(Thread.currentThread().getName() + ": methodB sleep stop");
        } finally {
          lock.unlock();
        }
    }
}
