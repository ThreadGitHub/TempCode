package org.juc.future.future_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTaskk while轮训获取结果
 * @author thread
 * @date 2023/9/25 22:33
 */
public class FutureTaskWhileDone {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(()-> {
            TimeUnit.SECONDS.sleep(5);
            return "task value.";
        });

        // run方法也是阻塞运行的
//        futureTask.run();

        // 启动线程
        new Thread(futureTask).start();

        while (true) {
            if (futureTask.isDone()) {
                String value = futureTask.get();
                System.out.println(value);
                break;
            } else {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("未获取到结果");
            }
        }
    }
}
