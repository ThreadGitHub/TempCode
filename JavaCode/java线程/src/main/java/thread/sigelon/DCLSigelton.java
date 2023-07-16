package thread.sigelon;

import java.util.concurrent.*;

/**
 * 双检锁实现单例模式问题
 */
public class DCLSigelton {
    private static DCLSigelton instance;

    private int num = 100;
    private String name = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private Integer num2 = new Integer(324235325);

    @Override
    public String toString() {
        return "DCLSigelton{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", num2=" + num2 +
                '}';
    }

    private DCLSigelton() {
        System.out.println("construct");
    }

    public static DCLSigelton newInstance() {
        // 双检锁实现单例
        if (instance == null) {
            synchronized (DCLSigelton.class) {
                if (instance == null) {
                    instance = new DCLSigelton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 100,
                TimeUnit.SECONDS, new LinkedBlockingDeque());
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(DCLSigelton.newInstance());
            });
        }
        threadPoolExecutor.shutdown();
    }
}
