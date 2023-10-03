package org.juc.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 比较四种累加的执行效率
 * 1.synchronized
 * 2.AtomicLong
 * 3.LongAdder
 * 4.LongAccumulator
 * @author thread
 * @date 2023/10/3 16:01
 */
public class LongAccumulatorCompareDemo {
    public static final int THREAD_NUM = 100;

    public static final int FOR_NUM = 10000 * 100;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        synchronizedDemo();
        atomicLongDemo();
        longAdderDemo();
        longAccumulator();
    }

    public static void synchronizedDemo() {
        ClickNumber clickNumber = new ClickNumber();
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(()-> {
                for (int j = 0; j < FOR_NUM; j++) {
                    clickNumber.clickBySynchronized();
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("synchronized耗时：" + (System.currentTimeMillis() - start) + " num= " + clickNumber.num);
    }

    public static void atomicLongDemo() {
        ClickNumber clickNumber2 = new ClickNumber();
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch2 = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(()-> {
                for (int j = 0; j < FOR_NUM; j++) {
                    clickNumber2.clickByAtomicLong();
                }
                countDownLatch2.countDown();
            }).start();
        }
        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("AtomicLong耗时：" + (System.currentTimeMillis() - start) + " num= " + clickNumber2.atomicLong.get());
    }

    public static void longAdderDemo() {
        ClickNumber clickNumber3 = new ClickNumber();
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch3 = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                for (int j = 0; j < FOR_NUM; j++) {
                    clickNumber3.clickByLongAdder();
                }
                countDownLatch3.countDown();
            }).start();
        }
        try {
            countDownLatch3.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("LongAdder耗时：" + (System.currentTimeMillis() - start) + " num= " + clickNumber3.longAdder.sum());
    }

    public static void longAccumulator() {
        ClickNumber clickNumber4 = new ClickNumber();
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch4 = new CountDownLatch(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(()-> {
                for (int j = 0; j < FOR_NUM; j++) {
                    clickNumber4.clickByLongAccumulator();
                }
                countDownLatch4.countDown();
            }).start();
        }
        try {
            countDownLatch4.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("LongAccumulator耗时：" + (System.currentTimeMillis() - start) + " num= " + clickNumber4.longAccumulator.get());
    }
}

class ClickNumber {
    int num = 0;
    public synchronized void clickBySynchronized() {
        num++;
    }

    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong() {
        atomicLong.incrementAndGet();
    }

    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left+right, 0);
    public void clickByLongAccumulator() {
        longAccumulator.accumulate(1);
    }
}
