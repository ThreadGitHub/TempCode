package org.juc.sync.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 锁膨胀时 hashcode 变化
 * 当一个对象处于偏向锁时 如果收到计算hashcode的情况会膨胀为重量级锁
 * @author thread
 * @date 2023/10/6 00:20
 */
public class HashCodeDemo {
    public static void main(String[] args) throws InterruptedException {
        // 保证开启偏向锁 跳过4秒延时
        TimeUnit.SECONDS.sleep(5);

        Object lock = new Object();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            System.out.println("main线程获取到偏向锁");
            // 立刻计算hashcode
            lock.hashCode();
        }

        // 偏向锁已经膨胀为重量级锁
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
