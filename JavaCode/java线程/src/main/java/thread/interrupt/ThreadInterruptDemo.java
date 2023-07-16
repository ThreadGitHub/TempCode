package thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Thread interrupt实现线程中断的demo
 *  isInterrupted() 判断中断的标志是否为true
 *  interrupt() 把线程的中断的标志设为true
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": hello");
            }
        }, "Thread-A");
        thread.start();

        new Thread(()-> {
            thread.interrupt();
        }).start();
    }
}
