package thread.interrupt;

/**
 * Thread.interrupted() 方法示例
 *  作用：先返回 isInterrupt的中断标志位的状态，然后重置标志位为 false
 */
public class ThreadInterruptedDemo {
    public static void main(String[] args) {
        // 读取当前线程的中断标志位
        System.out.println(Thread.currentThread().getName() + "isInterrupted: " + Thread.currentThread().isInterrupted());

        // 设置中断标志位为 true
        Thread.currentThread().interrupt();
        System.out.println("01--isInterrupt: " + Thread.currentThread().isInterrupted());

        // 先返回标志位状态 然后 恢复中断标志位为false
        System.out.println("02--isInterrupt: " + Thread.interrupted());

        // 先返回标志位状态 然后 恢复中断标志位为false
        System.out.println("03--isInterrupt: " + Thread.interrupted());
    }
}
