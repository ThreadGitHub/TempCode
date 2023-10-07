package org.juc.sync.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 锁膨胀时 hashcode 变化
 * 当一个对象已经计算过hashcode 那么再无法成为偏向锁
 * @author thread
 * @date 2023/10/6 00:38
 */
public class HashCode2Demo {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);

        Object lock = new Object();
        // 仍是偏向锁 101
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // 计算hashcode
        lock.hashCode();

        synchronized (lock) {
            System.out.println("lock....");
            // 直接升级为轻量级锁 00
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        // 轻量级锁降为无锁状态 001
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
