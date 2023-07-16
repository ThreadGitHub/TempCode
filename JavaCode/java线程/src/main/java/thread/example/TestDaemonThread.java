package thread.example;

import java.util.concurrent.TimeUnit;

/**
 * 测速守护线程
 *  守护线程也叫辅助线程如果jvm中没有了用户线程只剩下了守护线程 jvm 会关闭
 *  用户线程为实际工作的线程，如果仍存在用户线程虚拟机就不会关闭
 */
public class TestDaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            while (true) {

            }
        },"守护线程");
        // 设置为守护线程
        thread.setDaemon(true);
        thread.start();

        int i = 5 /0 ;

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+ " over.");
    }
}
