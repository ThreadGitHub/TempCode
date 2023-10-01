package org.juc.volatile_demo;

/**
 * 读写简单示例
 * @author thread
 * @date 2023/9/30 22:21
 */
public class ReadWriteDemo {
    private volatile int num = 0;

    /**
     * 加值用synchronized锁保证原子性
     */
    public synchronized void setNum(){
        num++;
    }

    /**
     * 读取用volatile保证可见性
     * @return
     */
    public int getNum() {
        return num;
    }
}
