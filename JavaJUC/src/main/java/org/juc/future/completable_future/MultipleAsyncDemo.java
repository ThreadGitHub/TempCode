package org.juc.future.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 多个异步任务的结果处理
 * applyToEither 多个异步任务获取最快的结果
 * thenCombine 合并异步任务结果
 * @author thread
 * @date 2023/9/26 23:20
 */
public class MultipleAsyncDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------------------选执行速度最快-------------------");
        CompletableFuture.supplyAsync(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "v";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "v2";
        }), result -> {
            System.out.println(result + " is winner");
            return result;
        }).thenAccept(System.out::println);

        TimeUnit.SECONDS.sleep(3);

        System.out.println("-------------------执行结果合并-------------------");
        CompletableFuture.supplyAsync(()->{
            return 1;
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            return 2;
        }), (v1, v2) ->{
            System.out.println("合并结果: " + (v1 + v2));
            return v1+v2;
        }).thenAccept(System.out::println);
    }
}
