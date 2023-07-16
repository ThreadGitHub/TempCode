package thread.sync.comm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程 synchronized 交替执行通信过程
 */
public class TestSyncComm {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                100, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1));

        PeopleResource peopleResource = new PeopleResource();
        CountDownLatch countDownLatch = new CountDownLatch(4);

        threadPoolExecutor.execute(()-> {
            for (int i = 0; i < 10; i++) {
                peopleResource.doPeole();
            }
            countDownLatch.countDown();
        });

        threadPoolExecutor.execute(()-> {
            for (int i = 0; i < 10; i++) {
                peopleResource.doPeole();
            }
            countDownLatch.countDown();
        });

        threadPoolExecutor.execute(()-> {
            for (int i = 0; i < 10; i++) {
                peopleResource.sleepPoeple();
            }
            countDownLatch.countDown();
        });

        threadPoolExecutor.execute(()-> {
            for (int i = 0; i < 10; i++) {
                peopleResource.sleepPoeple();
            }
            countDownLatch.countDown();
        });

        countDownLatch.await();
        threadPoolExecutor.shutdown();
    }
}
