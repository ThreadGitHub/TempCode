package thread.threadpool;

import java.sql.Array;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        threadPoolExecutor.prestartCoreThread();
    }
}
