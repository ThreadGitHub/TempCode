package thread.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport HelloWorld 示例
 *  总结：
 *      LockSupport不需要先获取锁就可以使用不用放到同步代码块
 *      LockSupport不需要线程先阻塞后唤醒，颠倒顺序也没关系，
 *      可以先给当前线程unpack发放唤醒通信证，之后线程在pack阻塞的时候会直接被唤醒
 */
public class LockSupportHelloWorld {
    public static void main(String[] args) throws InterruptedException {
        // 开启线程A
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start. " + System.currentTimeMillis());

            // 睡眠3秒 等待线程B先 unpack() 自己
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 阻塞当前线程
            LockSupport.park();

            System.out.println(Thread.currentThread().getName() + " 被唤醒 " + System.currentTimeMillis());
        }, "Thread-A");

        // 启动线程A
        thread.start();

        // 先开启线程B，然后唤醒线程A
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + " 唤醒线程A " + System.currentTimeMillis());
            // 先唤醒线程A，这时候线程A还没有启动
            LockSupport.unpark(thread);
        }, "Thread-B").start();
    }
}
