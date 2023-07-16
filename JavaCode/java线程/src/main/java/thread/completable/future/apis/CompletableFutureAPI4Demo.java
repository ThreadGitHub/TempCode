package thread.completable.future.apis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture API的示例 -按速度进行选用
 *  applyToEither() 按速度选用任务结果
 */
public class CompletableFutureAPI4Demo {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));

        // 开启异步任务A 耗时5秒
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "taskA";
        }, threadPoolExecutor);

        // 开启异步任务B 耗时2秒
        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "taskB";
        }, threadPoolExecutor);

        // 根据计算速度进行选用结果
        taskA.applyToEither(taskB, (t)-> {
            System.out.println("获取到最快任务的结果: " + t);
            return t;
        });

        // 关闭线程池
        threadPoolExecutor.shutdown();
    }
}
