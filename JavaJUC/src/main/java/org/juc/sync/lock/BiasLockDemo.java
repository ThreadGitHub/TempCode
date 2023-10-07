package org.juc.sync.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * synchronized偏向锁
 * @author thread
 * @date 2023/10/5 19:51
 */

/**
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           05 90 80 66 (00000101 10010000 10000000 01100110) (1719701509)
 *       4     4        (object header)                           b9 7f 00 00 (10111001 01111111 00000000 00000000) (32697)
 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * VALUE
 * 05 90 80 66 (00000101  最后三位 101 为偏向锁
 */
public class BiasLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // 这里暂停五秒的原因是因为默认jvm对于偏向锁的参数有延迟4秒钟
        // 否则设置jvm参数 -XX:BiasedLockingStartupDelay=0
//        TimeUnit.SECONDS.sleep(5);

        Object lock = new Object();

        // 此时已经是偏向锁了 延迟启动时间是0 只是没有线程持有锁
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        //线程尝试获取锁
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " run............");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
