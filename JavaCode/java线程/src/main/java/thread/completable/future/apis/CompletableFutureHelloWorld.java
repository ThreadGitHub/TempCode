package thread.completable.future.apis;

import java.util.concurrent.*;

/**
 * CompletableFuture入门示例
 *  通过get方法获取异步任务结果
 *    runAsync()
 *    supplyAsync()
 */
public class CompletableFutureHelloWorld {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));

        // 创建异步任务
        // 执行无返回值的异步任务
        CompletableFuture.runAsync(()-> {
            System.out.println(Thread.currentThread().getName() +  ": run task....");
        }, threadPoolExecutor);

        // 带返回值的异步任务
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() +  ": run task2....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task result.";
        }, threadPoolExecutor);

        // 获取任务结果
        String taskResult = stringCompletableFuture.get();
        System.out.println(taskResult);

        // 关闭线程池
        threadPoolExecutor.shutdown();
    }
}
