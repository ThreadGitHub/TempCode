package thread.completable.future.apis;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture API的示例 -任务合并
 *  thenCombine() 任务结果合并
 */
public class CompletableFutureAPI5Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步任务A
        CompletableFuture<Integer> taskA = CompletableFuture.supplyAsync(() -> {
            return 1;
        });

        // 异步任务B
        CompletableFuture<Integer> taskB = CompletableFuture.supplyAsync(() -> {
            return 2;
        });

        // 对计算结果进行合并
        taskA.thenCombine(taskB, (v1, v2) -> {
            return v1 + v2;
        }).whenComplete((v, e) -> {
            System.out.println("合并结果：" + v);
        }).exceptionally(e-> {
            System.out.println(e.getMessage());
            return null;
        });
    }
}
