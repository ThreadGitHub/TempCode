package org.juc.future.completable_future;

import java.util.concurrent.*;

/**
 * 异步任务使用
 * runAsync() 不带返回值的异步任务
 * supplyAsync() 带返回值的异步任务
 * whenComplete() 由调用线程处理异步任务结果(有无异常都会处理)
 * whenCompleteAsync() 由线程池处理异步结果(有无异常都会处理)
 * exceptionally() 处理异常
 * @author thread
 * @date 2023/9/26 19:50
 */
public class CompletableFutureAsyncTaskDemo {
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
            100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {
        System.out.println("--------默认线程池示例--------");
        // 默认线程池 ForkJoinPool
        CompletableFuture.runAsync(()->{
            System.out.println("["+ Thread.currentThread().getName() +"]run taskA...");
        }).thenRun(()->{
            System.out.println("run task2...");
        });

        CompletableFuture.supplyAsync(() -> {
            System.out.println("["+ Thread.currentThread().getName() +"]"+"run taskB...");
            // 模拟异常情况
//            int num = 1 /0;
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "task result.";
        }).whenComplete((v, e)-> {
            // 无异常 返回结果正常
            if (null == e) {
                System.out.println(Thread.currentThread().getName()+ ": " + v);
            }
        }).whenCompleteAsync((v, e)->{
            // 无异常 返回结果正常
            if (null == e) {
                System.out.println(Thread.currentThread().getName()+ ": " + v);
            }
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });


        System.out.println("--------线程池示例--------");
        // 指定线程池
        CompletableFuture.runAsync(()->{
            System.out.println("thread pool run async...");
        },threadPoolExecutor);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("thread pool run async...");
            return "task result...";
        }, threadPoolExecutor).whenComplete((v,e)->{
            // 无异常 返回结果正常
            if (null == e) {
                System.out.println(v);
            }
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
        threadPoolExecutor.shutdown();

        System.out.println("--------main线程执行完成--------");
    }
}
