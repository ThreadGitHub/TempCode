package org.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 已废弃使用
 * Thread.stop 方法中断线程运行
 * @author thread
 * @date 2023/9/28 16:36
 */
public class ThreadStopDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()-> {
            while (true) {
                System.out.println("run...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        // 终止线程 已废弃使用
        thread.stop();
        System.out.println("thread over.");
    }
}
