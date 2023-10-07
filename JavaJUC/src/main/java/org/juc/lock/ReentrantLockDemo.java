package org.juc.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁示例
 * 公平锁：竞争锁的线程都有机会获取到锁  大多数情况下的交替获取到锁
 * 非公平锁：竞争锁的线程谁先抢到锁算谁的 刚刚释放锁的线程很大概率又会获取到锁
 * @author thread
 * @date 2023/9/28 10:45
 */
public class ReentrantLockDemo {
    /**
     * 定义Lock锁
     * new ReentrantLock() 非公平锁
     */
//    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * new ReentrantLock(true) 公平锁
     */
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    /**
     * 模拟加锁卖票
     * @return
     */
    public int stock = 100;
    public int sale() {
        try {
            reentrantLock.lock();
            TimeUnit.MILLISECONDS.sleep(20);
            if (stock == 0) {
                System.out.println("卖完了。。。");
            }
            stock--;
            return stock;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo fairLockDemo = new ReentrantLockDemo();
        CompletableFuture.runAsync(()-> {
            while (fairLockDemo.sale() > 0) {
                System.out.println("Thread-A卖出一张票");
            }
        });
        CompletableFuture.runAsync(()-> {
            while (fairLockDemo.sale() > 0) {
                System.out.println("Thread-B卖出一张票");
            }
        });
        CompletableFuture.runAsync(()-> {
            while (fairLockDemo.sale() > 0) {
                System.out.println("Thread-C卖出一张票");
            }
        });

        Thread.currentThread().join();
    }
}
