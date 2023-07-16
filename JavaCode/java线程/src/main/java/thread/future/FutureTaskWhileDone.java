package thread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * while轮询获取FutureTask异步任务的结果
 */
public class FutureTaskWhileDone {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步任务
        FutureTask<String> futureTask = new FutureTask<>(()-> {
            TimeUnit.SECONDS.sleep(5);
           return "task result";
        });

        //开启异步线程执行
        Thread thread = new Thread(futureTask);
        thread.start();

        // 是否完成
        while (true) {
            // 通过 isDone() 判断异步任务是否结束
            if (futureTask.isDone()) {
                String result = futureTask.get();
                System.out.println(result);
                break;
            } else {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("获取结果中....");
            }
        }
    }
}
