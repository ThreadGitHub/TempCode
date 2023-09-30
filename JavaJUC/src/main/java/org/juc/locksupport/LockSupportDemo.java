package org.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 示例
 * 不需要持有锁就可以等待和唤醒
 * @author thread
 * @date 2023/9/29 23:16
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(()->{
            // 后等待
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ThreadA awit....");
            // 设置为等待状态
            LockSupport.park();

            System.out.println("ThreadA run....");
        }, "ThreadA");

        // 线程A启动
        threadA.start();

        // 先唤醒
        LockSupport.unpark(threadA);
    }
}
