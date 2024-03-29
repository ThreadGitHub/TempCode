package org.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入读写锁 ReentrantReadWriteLock 示例
 * @author thread
 * @date 2023/10/7 12:12
 */
public class ReentrantReadWriteLockDemo {
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(ReentrantReadWriteLockDemo::write).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(ReentrantReadWriteLockDemo::read).start();
        }
    }

    public static void write() {
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            System.out.println("开始写入...");
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        try {
            System.out.println("开始读取...");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }
}
