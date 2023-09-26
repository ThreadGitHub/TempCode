package org.juc.future.future_task;

import java.util.concurrent.*;

/**
 * FutureTask简单示例
 * @author thread
 * @date 2023/9/25 20:33
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {
        // 阻塞获取异步任务结果
        FutureTask<String> futureTask = new FutureTask<>(() -> "callable.");
        futureTask.run();
        String value = futureTask.get();
        System.out.println(value);

        // 超时获取异步任务结果
        FutureTask<String> stringFutureTask = new FutureTask<String>(new MyTask());
        // 超时获取要先get后run
        String svalue = stringFutureTask.get(3, TimeUnit.SECONDS);
        stringFutureTask.run();
        System.out.println(svalue);
    }
}

class MyTask implements Callable {
    @Override
    public Object call() throws Exception {
        TimeUnit.MINUTES.sleep(5);
        return "hello callable.";
    }
}