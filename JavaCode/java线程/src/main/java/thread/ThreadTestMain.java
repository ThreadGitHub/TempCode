package thread;

import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class ThreadTestMain {
    public static void main(String[] args) throws InterruptedException {

    }

    public static void cyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(()-> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("start " + Thread.currentThread().getName() + System.currentTimeMillis());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(System.currentTimeMillis());
        }, "Thread-A").start();

        new Thread(()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("start " + Thread.currentThread().getName() + System.currentTimeMillis());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(System.currentTimeMillis());
        }, "Thread-B").start();
    }

    public static void countDownLatch() {
        System.out.println("start.");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 开辟线程
        new Thread(()-> {
            int index = 0;
            while (index < 3) {
                index++;
                System.out.println("aaa");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            countDownLatch.countDown();
        }).start();

        //阻塞
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end.");
    }
}
