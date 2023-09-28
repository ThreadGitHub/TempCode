package org.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Volatile 标志位变量中断线程例子
 * @author thread
 * @date 2023/9/28 16:59
 */
public class VolatileFlagInterruptDemo {
    private volatile static boolean isInterrupt = false;

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
                if (isInterrupt) {
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
            isInterrupt = true;
            System.out.println("Thread-A over...");
        }).start();
    }
}
