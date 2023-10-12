package org.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入读写锁 ReentrantReadWriteLock 锁降级过程
 * @author thread
 * @date 2023/10/10 16:43
 */
public class ReentrantReadWriteLockLowerRankDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        // 写锁
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        // 读锁
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        // 线程获取到写锁
        writeLock.lock();
        try {
            System.out.println("开始写入。。。");
            TimeUnit.SECONDS.sleep(3);

            // 获取读锁
            readLock.lock();
            try {
                System.out.println("开始读取。。。");
                TimeUnit.SECONDS.sleep(2);
            } finally {
                // 释放写锁
                writeLock.unlock();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放读锁 在这里降级为了读锁
            readLock.unlock();
        }


        // t1 和 t2线程是互斥的
        new Thread(()-> {
            readLock.lock();
            try {
                System.out.println("t2线程开始读取。。。");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readLock.unlock();
            }
        }, "t2").start();

        // 在此期间 其他线程无法获取写锁
        new Thread(()-> {
            writeLock.lock();
            try {
                System.out.println("t1线程开始写入。。。");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                writeLock.unlock();
            }
        }, "t1").start();
    }
}
