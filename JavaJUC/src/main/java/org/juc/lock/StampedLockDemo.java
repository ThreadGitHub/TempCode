package org.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock示例 替换ReentrantReadWriteLock读写锁
 * @author thread
 * @date 2023/10/10 17:37
 */
public class StampedLockDemo {
    private static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(StampedLockDemo::write, "t1").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(StampedLockDemo::read, "t2").start();
        }
    }

    public static void write() {
        long stamp = stampedLock.writeLock();
        try {
            System.out.println("开始写入...");
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public static void read() {
        long stamp = stampedLock.readLock();
        try {
            System.out.println("开始读取...");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }
}
