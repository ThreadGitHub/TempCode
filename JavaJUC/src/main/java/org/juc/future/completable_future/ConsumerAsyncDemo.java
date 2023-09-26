package org.juc.future.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 消费异步任务结果示例
 * thenAccept 无异常处理 无异常处理遇到异常终止运行
 * whenComplete 消费任务结果 并处理异常
 * @author thread
 * @date 2023/9/26 23:10
 */
public class ConsumerAsyncDemo {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()-> {
//            int num = 1 / 0;
            return "task value";
        }).thenAccept(v-> {
            System.out.println("任务结果:" + v);
        });

        System.out.println("------------------");

        CompletableFuture.supplyAsync(()-> {
            int num = 1 / 0;
            return "task value";
        }).whenComplete((v, e)-> {
            System.out.println("任务结果:" + v);
            System.out.println("异常信息：" + e);
        });
    }
}
