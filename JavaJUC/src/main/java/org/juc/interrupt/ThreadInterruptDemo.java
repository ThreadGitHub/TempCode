package org.juc.interrupt;

/**
 * Thread类的 interrupt 标志位中断线程
 * @author thread
 * @date 2023/9/28 17:15
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            Thread thread = Thread.currentThread();
            while (true) {
                System.out.println(thread.getName() + " run...");

                // 判断标志位
                if (thread.isInterrupted()) {
                    break;
                }
            }
        }, "Thread-A");
        threadA.start();

        // 修改标志位 中断线程A
        new Thread(threadA::interrupt).start();

        // 死掉的线程再获取标志位会变为false
        System.out.println(threadA.isInterrupted());
    }
}
