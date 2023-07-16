package thread.completable.future.apis;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 对于完整的 complete 和 exception的使用
 *  whenComplete()  异步任务结果回调
 *  exceptionally() 异步任务异常处理回调
 */
public class CompletableFutureComplete {
    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));

        // 创建异步任务
        CompletableFuture.supplyAsync(()-> {
            System.out.println("异步任务开始执行....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 计算随机数 1-10
            int num = new Random().nextInt(10);
            if (num > 5) {
                num = num / 0;
            }
            return num;
        }, threadPoolExecutor).whenComplete((value, exception) -> {
            // 获取异步任务执行结果
            System.out.println("complete 获取任务结果： " + value);
        }).exceptionally((exception)-> {
            // 异步任务执行发生异常时的处理
            System.out.println("exceptionally: " + exception.getCause() + "\t" + exception.getMessage());
            return null;
        });

        // 关闭线程池
        threadPoolExecutor.shutdown();
    }
}
