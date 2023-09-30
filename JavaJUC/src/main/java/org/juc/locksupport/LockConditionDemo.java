package org.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock condition 实现等待唤醒demo
 * 不支持提前唤醒线程 必须要先 await 才能 signal
 * @author thread
 * @date 2023/9/30 12:38
 */
public class LockConditionDemo {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition condition = lock.newCondition();;
        new Thread(()-> {
           try {
               lock.lock();
               System.out.println("lock conditon await...");
               // 等待状态
               condition.await();
               System.out.println("lock condition run...");
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } finally {
               lock.unlock();
           }
        }).start();

        TimeUnit.SECONDS.sleep(3);

        // 唤醒线程A
        new Thread(()->{
            try {
                lock.lock();
                // 唤醒线程A
//                condition.signal();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
