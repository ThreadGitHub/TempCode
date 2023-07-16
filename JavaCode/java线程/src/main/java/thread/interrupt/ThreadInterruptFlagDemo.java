package thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 测试线程的Interrupt标志位的状态
 *  线程一启动 interrupt 标志位为 false
 *  线程调用 interrupt 方法会把标志位设置为true 并不会结束线程执行，但线程内部可以感知到标志位的改变
 *  线程结束时，会把 interrupt 标志位重新恢复为 false
 */
public class ThreadInterruptFlagDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i <300; i++) {
                System.out.println(i);
            }
            // 输出结果：true
            System.out.println("for end.线程interrupt标志状态：" + Thread.currentThread().isInterrupted());
        }, "Thread-A");
        thread.start();

        // 线程中断
        // 输出结果：false
        System.out.println("01--线程A的interrupt状态: " + thread.isInterrupted());

        thread.interrupt();
        // 输出结果：true
        System.out.println("02--线程A的interrupt状态: " + thread.isInterrupted());

        TimeUnit.SECONDS.sleep(6);
        // 输出结果：false 这时候线程已经运行结束，会自动恢复标志位所以是false
        System.out.println("03--线程A的interrupt状态: " + thread.isInterrupted());
    }
}
