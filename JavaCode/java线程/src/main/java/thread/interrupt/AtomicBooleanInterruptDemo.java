package thread.interrupt;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程中断问题，使用 AtomicBoolean 中断线程demo
 */
public class AtomicBooleanInterruptDemo {
    /**
     * 原子的Boolean值实现
     */
    static AtomicBoolean isStop = new AtomicBoolean(false);

    public static void main(String[] args) {
        new Thread(()-> {
            while (true) {
                // 判断是否中断标志是否修改
                if (isStop.get()) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": hello");
            }
        }, "Thread-A").start();

        new Thread(()-> {
            isStop.set(true);
        }, "Thread-B").start();
    }
}
