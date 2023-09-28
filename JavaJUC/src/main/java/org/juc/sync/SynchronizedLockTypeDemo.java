package org.juc.sync;

/**
 * Synchronized 三种使用方式
 * @author thread
 * @date 2023/9/28 00:41
 */
public class SynchronizedLockTypeDemo {
    /**
     * 字节码指令 flags: ACC_SYNCHRONIZED
     */
    public synchronized void methodA() {
        System.out.println("同步方法");
    }

    /**
     * 字节码指令 flags: ACC_STATIC, ACC_SYNCHRONIZED
     */
    public static synchronized void methodB() {
        System.out.println("静态同步方法");
    }

    /**
     * 字节码指令 monitorenter + monitorexit 包围同步代码块
     */
    public void methodC() {
        synchronized (this) {
            System.out.println("同步代码块");
        }
    }
}
