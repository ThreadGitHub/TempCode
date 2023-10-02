package org.juc.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference 实现自旋锁
 * @author thread
 * @date 2023/10/2 15:32
 */
public class SpinLockUtil {
    public AtomicReference<Thread> reference = new AtomicReference<>();

    public void lock() {
        // 绑定自己线程引用加锁
        while (!reference.compareAndSet(null, Thread.currentThread())) {

        }
        System.out.println(Thread.currentThread().getName() + ": 加锁成功");
    }

    public void unlock() {
        // 依然使用CAS 只能自己线程解锁
        while (!reference.compareAndSet(Thread.currentThread(), null)) {

        }
        System.out.println(Thread.currentThread().getName() + ": 解锁成功");
    }
}