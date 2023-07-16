package thread.jvm.threadtype;

public class ThreadTypeTest {
    public static void main(String[] args) {
        Thread thread = new Thread(()-> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
            }
        });

        //设置线程类型  false 非守护线程  true 守护线程
        thread.setDaemon(false);
        thread.start();

        System.out.println("主线程执行完毕");
    }
}