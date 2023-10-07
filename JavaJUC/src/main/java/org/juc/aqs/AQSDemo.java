package org.juc.aqs;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS测试程序
 * @author thread
 * @date 2023/10/6 14:54
 */
public class AQSDemo {
    public static void main(String[] args) throws InterruptedException {
        // 非公平锁
        ReentrantLock reentrantLock = new ReentrantLock();

        // 线程A先抢到锁然后不释放锁睡1个小时
        new Thread(()-> {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " run....");

                // 睡个一小时
                TimeUnit.MINUTES.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
              reentrantLock.unlock();
            }
        }, "Thread-A").start();

        // 线程B尝试抢占锁但是抢不到会加入到队列中并陷入等待状态
        Thread threadB = new Thread(() -> {
            reentrantLock.lock();
            try {

                System.out.println(Thread.currentThread().getName() + " run....");
            } finally {
                reentrantLock.unlock();
            }
        }, "Thread-B");
        threadB.start();

        // 线程B尝试抢占锁但是抢不到会加入到队列中并陷入等待状态
        new Thread(()-> {
            reentrantLock.lock();
            try {

                System.out.println(Thread.currentThread().getName() + " run....");
            } finally {
                reentrantLock.unlock();
            }
        }, "Thread-C").start();


        TimeUnit.SECONDS.sleep(2);
        // 打断线程B
        threadB.interrupt();
    }
}
