package org.juc.volatile_demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 双检锁 单例模式
 * 为了避免创建对象时指令重排 通过volatile限制重排序
 * @author thread
 * @date 2023/10/1 20:17
 */
public class DoubleCheckSinglonDemo {
    private static volatile DoubleCheckSinglonDemo singlon;

    private String str;
    private DoubleCheckSinglonDemo(){
        this.str = "init str";
    }

    @Override
    public String toString() {
        return "DoubleCheckSinglonDemo{" +
                "str='" + str + '\'' +
                '}';
    }

    public static DoubleCheckSinglonDemo getInstance() {
        // 双检锁创建对象
        if (null == singlon) {
            synchronized (DoubleCheckSinglonDemo.class) {
                if (null == singlon) {
                    singlon = new DoubleCheckSinglonDemo();
                }
            }
        }
        return singlon;
    }

    public static void main(String[] args) {
        int threadNum = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(DoubleCheckSinglonDemo.getInstance());
            }).start();
        }
    }
}
