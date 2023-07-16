package thread.future;

import java.util.concurrent.*;

/**
 * FutureTask + 线程池的形式实现多任务提升效率
 */
public class FutureTaskThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        singleMainTask();
        threadPoolTask();
    }

    /**
     * 线程池方式执行3个任务
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void threadPoolTask() throws ExecutionException, InterruptedException {
        // 开始时间
        long startTime = System.currentTimeMillis();

        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 100,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>());

        // 任务1
        FutureTask<String> futureTask1 = new FutureTask(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1";
        });
        threadPoolExecutor.submit(futureTask1);

        // 任务2
        FutureTask<String> futureTask2 = new FutureTask(()->{
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2";
        });
        threadPoolExecutor.submit(futureTask2);

        // 任务3
        FutureTask<String> futureTask3 = new FutureTask(()->{
            TimeUnit.MILLISECONDS.sleep(200);
            return "task3";
        });
        threadPoolExecutor.submit(futureTask3);

        // 收集结果
        String task1Result = futureTask1.get();
        String task1Result2 = futureTask2.get();
        String task1Result3 = futureTask3.get();

        // 结束时间
        long stopTime = System.currentTimeMillis();

        // 关闭线程池
        threadPoolExecutor.shutdown();

        System.out.println("耗时: " + (stopTime - startTime));
        System.out.println(task1Result + "\t" + task1Result2 + "\t" + task1Result3);
    }

    /**
     * 单个Main线程执行三个任务
     * @throws InterruptedException
     */
    public static void singleMainTask() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(500);
        Thread.sleep(300);
        Thread.sleep(200);
        long stopTime = System.currentTimeMillis();
        System.out.println("耗时: " + (stopTime - startTime));
    }
}