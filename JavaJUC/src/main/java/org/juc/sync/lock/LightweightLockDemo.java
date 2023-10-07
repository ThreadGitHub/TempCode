package org.juc.sync.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * synchronized轻量级锁示例
 * @author thread
 * @date 2023/10/5 23:35
 */

/**
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           e8 59 2a 07 (11101000 01011001 00101010 00000111) (120216040)
 *       4     4        (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * VALUE
 * e8 59 2a 07 (11101000  --末尾00表示升级为轻量级锁
 */
public class LightweightLockDemo {

    public static void main(String[] args) throws InterruptedException {
        // 先睡5秒等待偏向锁启动
        TimeUnit.SECONDS.sleep(5);

        Object lock = new Object();
        // 101 轻量级锁启动
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // t1线程获取锁 偏向锁偏向线程t1
        new Thread(()->{
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " run............");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        },"t1").start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // t2线程获取锁 偏向锁遇到竞争升级为轻量级锁 最后两位锁标志位 00
        new Thread(()->{
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " run............");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        },"t2").start();


        TimeUnit.SECONDS.sleep(2);
        // 轻量级锁降为无锁 最后三位锁标志为 001
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        // 升级到轻量级锁后即便降级为无锁 只要再加锁直接就是轻量级锁
        System.out.println("升级为轻量级锁后的再加锁。。。。");
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
