package thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 异步任务的 HelloWorld
 */
public class FutureTaskHelloWorld {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread threadClass = new MyThread();
        FutureTask<String> futureTask = new FutureTask(threadClass);
        Thread thread = new Thread(futureTask);
        thread.start();
        String result = futureTask.get();
        System.out.println(result);
    }
}

class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello futureTask.";
    }
}