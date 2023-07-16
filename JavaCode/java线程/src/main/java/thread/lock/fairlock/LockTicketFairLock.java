package thread.lock.fairlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 根据卖票测试公平锁和非公平锁
 *  公平锁即多个线程均匀的抢占锁
 *  非公平锁即多个线程谁先抢到谁就执行，因为线程在释放同步的时候立即获取锁的概率是很大的，所以会出现一个线程一直执行的情况
 *  new ReentrantLock() 默认是非公平锁
 *  new ReentrantLock(true) 为true是公平锁
 */
public class LockTicketFairLock {
    private int size;

    public LockTicketFairLock(int size) {
        this.size = size;
    }

    // 默认非公平锁
    private ReentrantLock notFairReentrantLock = new ReentrantLock();

    // 公平锁
    private ReentrantLock fairReentrantLock = new ReentrantLock(true);

    public void sale() {
        //上锁
        fairReentrantLock.lock();
        try {
            if (size > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出：[" + size-- + "]号票, 剩余：" + size);
                // 睡眠一段时间来显示出超卖的现象
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            //解锁
            fairReentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        LockTicketFairLock resouce = new LockTicketFairLock(30);

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                resouce.sale();
            }
        }, "thread-A").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                resouce.sale();
            }
        }, "thread-B").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                resouce.sale();
            }
        }, "thread-C").start();
    }
}
