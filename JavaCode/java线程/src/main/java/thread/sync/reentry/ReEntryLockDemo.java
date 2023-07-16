package thread.sync.reentry;

/**
 * 可重入锁的示例
 *  可重入锁即表示在同一个锁对象中，同步方法和和同步代码块可以互相调用
 *  占有锁的线程在互相调用的时候可以直接获取锁
 */
public class ReEntryLockDemo {
    public synchronized void methodA() {
        System.out.println("methodA start");
        methodB();
    }

    private synchronized void methodB() {
        System.out.println("methodB start");
        methodC();
    }

    private synchronized void methodC() {
        System.out.println("methodC start");
    }

    public static void main(String[] args) {
        // 可重入锁的代码块示例
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("外部");
            synchronized (lock) {
                System.out.println("中部");
                synchronized (lock) {
                    System.out.println("内部");
                }
            }
        }

        // 可重入锁的方法示例
        new ReEntryLockDemo().methodA();
    }
}
