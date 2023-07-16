package thread.completable.future.apis;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture API使用 -对任务进行处理的API
 *  thenApply() 对异步结果的处理
 *  handle()  对异步结果的处理
 *  whenComplete() 对异步结果的处理
 *  exceptionAlly() 对异步结果异常的处理
 */
public class CompletableFutureAPI2Demo {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> 1).thenApply((value) -> {
            value += 2;
            System.out.println("thenApply-A start. value: " + value);
            return value;
        }).thenApply((value) -> {
            value += 2;
            int n = 10 / 0;
            System.out.println("thenApply-B start. value: " + value);
            return value;
        }).thenApply((value) -> {
            value += 2;
            System.out.println("thenApply-C start. value: " + value);
            return value;
        }).whenComplete((value, exception) -> {
            System.out.println("whenComplete value: " + value);
        }).exceptionally(exception -> {
            System.out.println("exceptionally: " + exception.getMessage());
            return null;
        });

        System.out.println("----------------------------------------------------");

        CompletableFuture.supplyAsync(() -> 1).handle((value, ex) -> {
            value += 2;
            System.out.println("handle-A start. value: " + value);
            return value;
        }).handle((value, ex) -> {
            value += 2;
            // 这里触发异常
            int n = 10 / 0;
            System.out.println("handle-B start. value: " + value);
            return value;
        }).handle((value, ex) -> {
            // 在这里根据异常继续处理
            if (ex != null) {
                value = 1;
            }
            value += 2;
            System.out.println("handle-C start. value: " + value);
            return value;
        }).whenComplete((value, exception) -> {
            System.out.println("whenComplete value: " + value);
        }).exceptionally(exception -> {
            System.out.println("exceptionally: " + exception.getMessage());
            return null;
        });
    }
}
