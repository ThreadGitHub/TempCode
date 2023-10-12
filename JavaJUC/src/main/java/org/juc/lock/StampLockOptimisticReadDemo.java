package org.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampLock 乐观读的操作  读的过程中可以有写锁的介入
 * @author thread
 * @date 2023/10/10 18:12
 */
public class StampLockOptimisticReadDemo {
    static StampedLock stampedLock = new StampedLock();
    static int number = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(StampLockOptimisticReadDemo::tryOptimisticRead);
        threadA.start();
        System.out.println(number);

        Thread threadB = new Thread(() -> {
            long l = stampedLock.writeLock();
            number++;
            System.out.println("写锁修改成功。。。");
            stampedLock.unlockWrite(l);
        }, "t1");
        threadB.start();

        threadA.join();
        threadB.join();
        System.out.println(number);
    }

    /**
     * 乐观读
     */
    public static void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 如果有其他线程尝试加入写锁 升级为悲观读
            if (!stampedLock.validate(stamp)) {
                System.out.println("被修改");
                long l = stampedLock.readLock();
                number++;
                stampedLock.unlockRead(l);
            } else {
                // 乐观读增加
                number++;
            }
        }
    }
}
