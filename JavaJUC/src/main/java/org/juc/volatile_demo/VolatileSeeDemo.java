package org.juc.volatile_demo;

import java.util.concurrent.TimeUnit;

/**
 * Volatile 可见性demo
 * 线程不会立马感知到while循环flag变量改为了false
 * while(flag){} 中不要有内容如果用println打印方法里使用了synchronized会刷新主存
 * @author thread
 * @date 2023/10/1 16:28
 */
public class VolatileSeeDemo {
    /*volatile*/ static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag) {

            }
            System.out.println("修改Flag为false线程停止");
        }).start();

        TimeUnit.SECONDS.sleep(3);
        flag = false;
        System.out.println("main修改完成");
    }
}
