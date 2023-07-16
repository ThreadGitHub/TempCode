package thread.lock.comm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 锁的线程通信示例
 * Lock锁通过 condition 的 await 和 signal 实现线程的唤醒和等待
 *  await() 和 signal() 方法都需要先获取 Lock锁才能执行
 */
public class LockConditionDemo {
    // lock锁
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 唤醒条件对象
        Condition condition = reentrantLock.newCondition();

        // 创建线程A并阻塞
        new Thread(()-> {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": start.");

                // 阻塞线程
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": 被唤醒");
            } finally {
                reentrantLock.unlock();
            }
        }, "Thread-A").start();

        // 睡眠1秒钟等待 线程A启动
        TimeUnit.SECONDS.sleep(1);

        // 创建线程B 并 获取Lock锁 然后 唤醒线程A
        new Thread(()-> {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": 唤醒线程A");
                // 唤醒线程A
                condition.signal();
            } finally {
                reentrantLock.unlock();
            }

        },"Thread-B").start();
    }
}
