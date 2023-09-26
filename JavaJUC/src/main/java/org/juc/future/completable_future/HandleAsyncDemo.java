package org.juc.future.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 对异步任务继续处理示例
 * thenApply 无异常处理结果传递处理
 * handle 处理异常并继续运行
 * @author thread
 * @date 2023/9/26 23:00
 */
public class HandleAsyncDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------thenApply------------");
        // thenApply 无异常传递处理
        CompletableFuture.supplyAsync(()-> {
            System.out.println(Thread.currentThread().getName() + " 处理中");
            return "11";
        }).thenApply(v -> {
            System.out.println(Thread.currentThread().getName() + " 处理中");
            return v + "22";
        }).thenApply(v->{
            System.out.println(Thread.currentThread().getName() + " 处理中");
            return v + "33";
        }).thenAccept(result->{
            System.out.println("最终结果：" + result);
        });

        System.out.println("------------handle------------");
        CompletableFuture.supplyAsync(()-> {
            System.out.println(Thread.currentThread().getName() + " 处理中");
            return "11";
        }).handleAsync((v, e) -> {
//            int num = 1/0;
            System.out.println(Thread.currentThread().getName() + " 处理中");
            return v + "22";
        }).handle((v, e) -> {
            System.out.println(Thread.currentThread().getName() + " 处理中");
            return v + "22";
        }).thenAccept(result-> {
            System.out.println("最终结果：" + result);
        });
        Thread.currentThread().join();
    }
}
