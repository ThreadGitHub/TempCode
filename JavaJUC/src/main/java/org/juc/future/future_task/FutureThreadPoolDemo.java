package org.juc.future.future_task;

import java.util.concurrent.*;

/**
 * FutureTask配合线程池使用
 * @author thread
 * @date 2023/9/25 22:13
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建异步任务 FutureTask
        long start = System.currentTimeMillis();
        FutureTask<String> futureTask = new FutureTask<>(()-> {
            TimeUnit.SECONDS.sleep(2);
            return "task-1.";
        });

        FutureTask<String> futureTask2 = new FutureTask<>(()-> {
            TimeUnit.SECONDS.sleep(4);
            return "task-2.";
        });

        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        threadPoolExecutor.submit(futureTask);
        threadPoolExecutor.submit(futureTask2);

        // 获取异步任务结果
        String v1 = futureTask.get();
        String v2 = futureTask2.get();
        System.out.println(v1);
        System.out.println(v2);

        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
        threadPoolExecutor.shutdown();
    }
}