package thread.completable.future.apis;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CompletableFuture API使用 -获取任务结果的API
 *  get() 阻塞获取结果
 *  get(long timeout, TimeUnit unit) 等待指定时间获取结果
 *  getNow(T valueIfAbsent) 立即获取结果如果任务未完成返回给定值
 *  join()  阻塞获取结果，但不抛出异常，由join内部打印异常堆栈
 *  complete(T value) 如果任务未完成那么设置固定返回值，后续所有获取异步任务都会得到这个给定值
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) {
        // 开启异步任务
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            // 模拟任务耗时处理两秒
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task result";
        });

        try {
            String taskResult = completableFuture.get(1, TimeUnit.SECONDS);
            System.out.println(taskResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        // 立即获取结果，如果异步任务未完成返回给定值
        String value = completableFuture.getNow("value");
        System.out.println(value);

        // join阻塞获取结果
        String joinResult = completableFuture.join();
        System.out.println(joinResult);

        // 如果异步任务未结束，赋予默认返回值然后后续调用get 或者 join等方法都返回给定的值
        completableFuture.complete("completeDefaultValue");

        // 阻塞获取异步任务结果
        try {
            String taskResult = completableFuture.get();
            System.out.println(taskResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 等待指定时间获取异步任务结果
        try {
            String taskResult = completableFuture.get(10, TimeUnit.SECONDS);
            System.out.println(taskResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
