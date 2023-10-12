package org.juc.happens_before;

/**
 * 线程 start 原则
 * 主线程 A 启动子线程 B 后，子线程 B 能够看到主线程在启动子线程 B 前的操作
 * @author thread
 * @date 2023/10/12 16:41
 */
public class HappensBeforeThreadStartDemo {
    static int var = 0;
    public static void main(String[] args) {
        Thread B = new Thread(()->{
            // 主线程调用B.start()之前
            // 所有对共享变量的修改，此处皆可见
            // 此例中，var==77
            System.out.println(var);
        });
        // 此处对共享变量var修改
         var = 77;
        // 主线程启动子线程
        B.start();
    }
}