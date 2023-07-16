package thread.sync.ticket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试synchronized并发卖票
 */
public class TestTicketSync {
    public static void main(String[] args) throws InterruptedException {
        //十个线程的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 10,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(1));

        CountDownLatch countDownLatch = new CountDownLatch(10);

        // 创建30张票的资源
        TicketResource ticketResource = new TicketResource(30);

        long startTime = System.currentTimeMillis();

        //开10个任务卖票
        for (int i = 0; i < 100; i++) {
            //多线程售卖 每个线程都有30次卖的机会
            threadPoolExecutor.execute(() -> {
                for (int j = 1; j< 30; j++) {
                    ticketResource.sale();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        //统计耗时
        long stopTIme = System.currentTimeMillis();
        System.out.println(stopTIme - startTime);

        threadPoolExecutor.shutdown();
    }
}
