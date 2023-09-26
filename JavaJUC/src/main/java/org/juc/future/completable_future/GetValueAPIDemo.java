package org.juc.future.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CompletableFuture 常用APi示例
 * getNow("value2") 立即获取结果
 * get() 阻塞获取结果
 * get(10, TimeUnit.SECONDS) 超时获取结果
 * join() 获取结果
 * @author thread
 * @date 2023/9/26 21:45
 */
public class GetValueAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "value";
        });

        // 立即获取结果 未完成返回给定值
        String value = completableFuture.getNow("value2");
        System.out.println(value);

        // 阻塞获取结果
        value = completableFuture.get();
        System.out.println(value);

        // 超时获取结果
        value = completableFuture.get(10, TimeUnit.SECONDS);
        System.out.println(value);

        value = completableFuture.join();
        System.out.println(value);
    }
}
