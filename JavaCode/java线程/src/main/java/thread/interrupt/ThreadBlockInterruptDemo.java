package thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 当Thread.interrupt() 遇到正在阻塞状态的线程的情况
 *  当前线程如果处于等待或阻塞状态那么就会在 sleep 或者 wati方法中抛出InterruptedException
 *  需要在catch中接着调用 interrupt() 设置标志位中断当前线程
 *  sleep interrupted
 */
public class ThreadBlockInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 判断标志位是否为true，打断线程执行
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " 线程已被中断");
                    break;
                }

                System.out.println("hello. " + System.currentTimeMillis());

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    // 如果其他线程中断失败那么会直接抛出异常，在异常 catch 中继续中断当前线程
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }, "Thread-A");
        // 启动线程A
        thread.start();

        // 等待线程A运行一段时间
        TimeUnit.MILLISECONDS.sleep(20);

        // 修改中断标志位为true
        thread.interrupt();
    }
}