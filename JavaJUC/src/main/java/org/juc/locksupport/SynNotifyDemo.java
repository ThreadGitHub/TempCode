package org.juc.locksupport;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized锁 notify 实现等待和唤醒
 * notify() 随机从等待队列中唤醒一个
 * notifyall() 唤醒全部等待线程
 * @author thread
 * @date 2023/9/29 23:24
 */
public class SynNotifyDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                System.out.println("ThreadA wait ....");

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("ThreadA run ....");
            }
        });
        threadA.start();

        TimeUnit.SECONDS.sleep(3);

        // 线程B获取锁去唤醒线程A
        new Thread(()->{
           synchronized (lock) {
               // 唤醒ThreadA
               lock.notifyAll();
           }
        }).start();
    }
}
