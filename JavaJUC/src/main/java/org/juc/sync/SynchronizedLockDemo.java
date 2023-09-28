package org.juc.sync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 测试 synchronized 锁的对象或实例
 * @author thread
 * @date 2023/9/27 19:00
 */
public class SynchronizedLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();

        CompletableFuture.runAsync(resource::hello);

        // 线程A
        CompletableFuture.runAsync(resource::methodA);
        // 线程B
        CompletableFuture.runAsync(Resource::methodB);

        CompletableFuture.runAsync(resource::methodC);
        CompletableFuture.runAsync(resource::methodD);

        Thread.currentThread().join();
    }
}

class Resource {
    /**
     * 锁的是当前实例对象 相当于 synchronized(this){}
     */
    public synchronized void methodA() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("methodA");
    }

    /**
     * 锁的是类实例对象
     */
    public synchronized void methodC() {
        synchronized (this) {
            System.out.println("methodC");
        }
    }

    /**
     * 锁的是当前类的class模版对象 相当于 synchronized(Resource.class){}
     */
    public static synchronized void methodB() {
        System.out.println("methodB");
    }

    /**
     * 锁的是类模版对象 .class
     */
    public void methodD() {
        synchronized (Resource.class) {
            System.out.println("methodD");
        }
    }

    /**
     * 无锁方法
     */
    public void hello() {
        System.out.println("hello");
    }
}
