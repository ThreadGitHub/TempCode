package thread.interrupt;

/**
 * 线程中断问题，使用 volatile 中断线程demo
 */
public class VolatileInterruptDemo {
    // 线程A 用于判断线程是否需要被中断
    private volatile static boolean isStop = false;

    public static void main(String[] args) {
        // 开启线程A
        new Thread(() -> {
            while (true) {
                // 判断是否需要结束
                if (isStop) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": hello.");
            }
        }, "Thread-A").start();

        // 线程B
        new Thread(()-> {
            // 设置中断
            isStop = true;
        }, "Thread-B").start();

//        CompletableFuture.runAsync(()-> {
//           isStop = true;
//        });
    }
}
