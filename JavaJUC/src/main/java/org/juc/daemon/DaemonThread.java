package org.juc.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程和用户线程示例
 * @author thread
 * @date 2023/9/25 19:56
 */
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        // 用户线程
        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "user-thread");
        userThread.start();

        // 守护线程
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "-" + System.currentTimeMillis());
            }
        }, "daemon-thread");
        // 设置为守护线程
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
