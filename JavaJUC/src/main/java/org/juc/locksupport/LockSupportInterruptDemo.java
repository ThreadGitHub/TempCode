package org.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 与 Thread.interrupt方法
 * @author thread
 * @date 2023/10/6 21:54
 */
public class LockSupportInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("线程运行");
            // 阻塞线程
            LockSupport.park(new Object());
            System.out.println("阻塞解除");
        });
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
