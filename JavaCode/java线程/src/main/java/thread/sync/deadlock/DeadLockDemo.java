package thread.sync.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁的demo和排查方式
 *  命令排查
 *      jps -l --查看全部java进程
 *      jstack 进程id --查看jvm堆栈信息   (Found 1 deadlock.)
 *      // 日志
 *      Java stack information for the threads listed above:
 *      ===================================================
 *       "thread-a":
 *              at thread.sync.deadlock.DeadLockDemo.lambda$main$1(DeadLockDemo.java:38)
 *              - waiting to lock <0x000000076b820888> (a java.lang.Object)
 *              - locked <0x000000076b820898> (a java.lang.Object)
 *              at thread.sync.deadlock.DeadLockDemo$$Lambda$2/1747585824.run(Unknown Source)
 *              at java.lang.Thread.run(Thread.java:748)
 *        "thread-a":
 *              at thread.sync.deadlock.DeadLockDemo.lambda$main$0(DeadLockDemo.java:28)
 *              - waiting to lock <0x000000076b820898> (a java.lang.Object)
 *              - locked <0x000000076b820888> (a java.lang.Object)
 *              at thread.sync.deadlock.DeadLockDemo$$Lambda$1/1078694789.run(Unknown Source)
 *              at java.lang.Thread.run(Thread.java:748)
*         Found 1 deadlock.
 *  图形化界面
 *      jconsole 检查死锁
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(()-> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "拿到lockA");

                //稍等一下让lockB被B线程获取 模拟情况
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "尝试获取-lockB");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "拿到lockB");
                }
            }
        }, "thread-a").start();

        new Thread(()-> {
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "拿到lockB");
                System.out.println(Thread.currentThread().getName() + "尝试获取-lockA");
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + "拿到lockA");
                }
            }
        }, "thread-b").start();
    }
}
