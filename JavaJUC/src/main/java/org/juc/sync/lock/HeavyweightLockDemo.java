package org.juc.sync.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 重量级锁示例
 * 重量级锁可以降级为轻量级锁
 * @author thread
 * @date 2023/10/6 00:04
 */

/**
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           4a 8d 01 cb (01001010 10001101 00000001 11001011) (-889090742)
 *       4     4        (object header)                           aa 7f 00 00 (10101010 01111111 00000000 00000000) (32682)
 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 *      12     4        (loss due to the next object alignment)
 *
 * VALUE
 * 4a 8d 01 cb (01001010  --末尾标志位为10 表示升级为重量级锁
 */
public class HeavyweightLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // t1独占锁
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " run...");

                // 这里暂停不释放锁然后让t2线程竞争 自旋达到上限进行锁升级
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1");
        t1.start();

        // 线程t2一致竞争t1锁  不断自旋 达到上限膨胀为重量级锁
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " run...");
            }
        }, "t2");
        t2.start();

        t1.join();
        t2.join();
        // 升级为重量级锁
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        System.out.println(lock.hashCode());

        // 降为无锁
        TimeUnit.SECONDS.sleep(2);
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // 轻量级锁
        System.out.println("降为无锁后的第一次加锁。。。。");
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // 轻量级锁
        System.out.println("降为无锁后的第二次加锁。。。。");
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
