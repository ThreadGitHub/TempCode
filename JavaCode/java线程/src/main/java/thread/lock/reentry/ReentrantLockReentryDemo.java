package thread.lock.reentry;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可重入锁的示例
 *  注意一定要保证 lock 和 unlock的一一对应不然就会出现阻塞问题
 *  加锁和解锁的数量不一致就会造成其他线程的阻塞
 */
public class ReentrantLockReentryDemo {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        // 线程A
        new Thread(()-> {
            // 第一次获取锁
            reentrantLock.lock();
            try {
                System.out.println("AAAA");

                reentrantLock.lock();
                // 第二次重入锁
                try {
                    System.out.println("BBB");
                } finally {
                    // 加锁和解锁的数量不一致就会造成其他线程的阻塞
//                    reentrantLock.unlock();
                }

            }finally {
                // 释放锁
                reentrantLock.unlock();
            }
        }).start();


        // 线程B
        new Thread(()-> {
            // 第三次获取锁
            reentrantLock.lock();
            try {
                System.out.println("CCCC");
            } finally {
                reentrantLock.unlock();
            }
        }).start();
    }
}
