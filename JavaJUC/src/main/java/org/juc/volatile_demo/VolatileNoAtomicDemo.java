package org.juc.volatile_demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * volatile 不能保证原子性的demo
 * 这里首先i++就不是原子操作它是多条jvm字节码指令
 * @author thread
 * @date 2023/10/1 17:28
 */
public class VolatileNoAtomicDemo {
    private volatile int num = 0;

    public /*synchronized*/ void addNum() {
        num++;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileNoAtomicDemo volatileNoAtomicDemo = new VolatileNoAtomicDemo();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    volatileNoAtomicDemo.addNum();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println(volatileNoAtomicDemo.num);
    }
}
