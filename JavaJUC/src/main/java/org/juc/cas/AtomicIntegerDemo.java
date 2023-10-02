package org.juc.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子int类
 * @author thread
 * @date 2023/10/2 14:51
 */
public class AtomicIntegerDemo {
    static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    integer.getAndIncrement();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(integer.get());
    }
}
