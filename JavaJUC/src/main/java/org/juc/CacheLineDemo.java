package org.juc;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * 通过填充CPU缓存行的操作来优化性能  一个缓冲行一般都是64字节
 * 缓存填充时间：56924666
 * 没用缓存填充时间：76023250
 *
 * jvm参数开启 @Contended 缓冲行填充注解
 * @author thread
 * @date 2023/10/13 00:24
 */
public class CacheLineDemo {
    private static long count = 1_0000_0000;

    @Contended
    private static class T {
        // 手动填充缓冲行
//        private long p1, p2, p3, p4, p5, p6, p7;

        private long x = 0;
        // 手动填充缓冲行
//        private long p9, p10, p11, p12, p13, p14, p15;
    }

    private static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[0].x = i;
            }
            countDownLatch.countDown();
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[1].x = i;
            }
            countDownLatch.countDown();
        });

        long start = System.nanoTime();
        threadA.start();
        threadB.start();
        countDownLatch.await();
        long stop = System.nanoTime();
        System.out.println(stop - start);
    }
}
