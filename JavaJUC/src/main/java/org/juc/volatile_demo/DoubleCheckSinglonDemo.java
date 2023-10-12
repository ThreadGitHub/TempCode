package org.juc.volatile_demo;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 双检锁 单例模式
 * 为了避免创建对象时指令重排 通过volatile限制重排序
 * @author thread
 * @date 2023/10/1 20:17
 */
public class DoubleCheckSinglonDemo {
    private static volatile DoubleCheckSinglonDemo singlon;

    private String str;

    private byte[] arr;

    private DoubleCheckSinglonDemo(){
        this.str = "测试测试测试测试测试";
        this.arr = new byte[1024];
    }

    @Override
    public String toString() {
        return "DoubleCheckSinglonDemo{" +
                "str='" + str + '\'' +
                ", arr=" + Arrays.toString(arr) +
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

    public static void main(String[] args) throws InterruptedException {
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
