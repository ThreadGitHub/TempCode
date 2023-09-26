package org.juc.future.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * thenRun 启动一个异步线程 没有上一步的结果也没有返回值给下一步处理
 * @author thread
 * @date 2023/9/26 23:14
 */
public class NewAsyncDemo {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()-> {
            return "task value";
        }).thenRun(()-> {
            System.out.println("新任务启动");
        }).thenRun(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("新任务启动");
        });
    }
}
