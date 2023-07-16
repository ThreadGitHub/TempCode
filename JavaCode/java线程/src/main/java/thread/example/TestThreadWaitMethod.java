package thread.example;

import org.junit.Test;

/**
 * 测试Object.wait方法使用
 *  wait方法需要持有monitor监视器的对象才可以调用
 */
public class TestThreadWaitMethod {
    private Object lock = new Object();

    @Test
    public void test() throws InterruptedException {
        synchronized (lock) {
            lock.wait(5000);
            System.out.println("wati finish...");
        }
        System.out.println("main finish...");
    }

    @Test
    public void test2() throws InterruptedException {
        synchronized (this) {
            this.wait(5000);
            System.out.println("wati finish...");
        }
        System.out.println("main finish...");
    }
}
