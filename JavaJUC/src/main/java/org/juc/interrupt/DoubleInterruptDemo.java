package org.juc.interrupt;

/**
 * 二次中断处理
 * 阻塞状态的线程 调用线程中断 interrupt 会报interruptException异常
 * java.lang.InterruptedException: sleep interrupted
 * @author thread
 * @date 2023/9/29 22:59
 */
public class DoubleInterruptDemo {
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (true) {
                System.out.println("threadA run ....");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 二次设置标志位
                    Thread.currentThread().interrupt();
                }

                // 判断标志位并退出
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        });
        threadA.start();

        // 中断线程A
        new Thread(threadA::interrupt).start();
    }
}
