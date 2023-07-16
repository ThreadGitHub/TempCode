package thread.completable.future.apis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture API的示例 -对异步结果进行消费
 *  thenRun 不需要获取异步结果的消费回调
 *  thenAccept 携带异步执行结果的回调消费
 */
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));

        // 开启异步任务
        CompletableFuture.supplyAsync(()-> 1, threadPoolExecutor).thenRun(()-> {
            // 对异步任务进行消费
            System.out.println("消费 then run...");
        }).whenComplete((v, e)->{
            // 因为已经被thenRun消费所以获取不到结果
            System.out.println("whenComplete: " + v +"\t" + e);
        });

        System.out.println("-----------------------------------------------------");

        // 开启异步任务
        CompletableFuture.supplyAsync(()->  987, threadPoolExecutor).thenApply(v-> {
            //对异步任务结果进行处理
            return v+=9;
        }).thenAccept(v -> {
            // 对异步任务进行消费
            System.out.println("thenAccept消费: " + v);
        }).whenComplete((v,e) -> {
            System.out.println("whenComplete: " + v +"\t" + e);
        });

        threadPoolExecutor.shutdown();
    }
}
