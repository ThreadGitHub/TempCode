package org.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 原子Boolean类标志位 中断线程示例
 * @author thread
 * @date 2023/9/28 17:06
 */
public class AtomicBooleanInterruptDemo {
    private static AtomicBoolean isInterrupt = new AtomicBoolean(false);

    public static void main(String[] args) {
        new Thread(()->{
            while (true) {
                System.out.println(Thread.currentThread().getName() + " run....");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // 判断标志位中断线程
                if (isInterrupt.get()) {
                    break;
                }
            }
        }, "Thread-A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isInterrupt.set(true);
            System.out.println("Thread-A over...");
        }).start();
    }
}
