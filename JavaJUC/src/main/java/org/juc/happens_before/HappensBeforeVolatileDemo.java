package org.juc.happens_before;

/**
 * 对于 volatile规则 和 次序规则的示例
 * @author thread
 * @date 2023/9/30 19:12
 */
public class HappensBeforeVolatileDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();

        new Thread(resource::write, "Thread-A").start();
        new Thread(resource::read, "Thread-B").start();
    }
}

class Resource {
    private int num = 0;
    private volatile boolean flag = false;

    /**
     * 模拟写入
     */
    public void write() {
        // 这里不是volatile变量 其他线程会读到10吗？
        num = 10;
        // volatile改为true
        flag = true;
    }

    /**
     * 模拟读取
     */
    public void read() {
        if (flag) {
            System.out.println(num);
        }
    }
}