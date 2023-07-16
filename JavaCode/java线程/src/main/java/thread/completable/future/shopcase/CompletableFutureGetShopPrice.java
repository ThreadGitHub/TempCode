package thread.completable.future.shopcase;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 模拟一个电商比价的功能，需要获取各个电商平台的价格然后返回一个价格列表
 * 1.直接用主线程获取三个电商平台的价格的耗时是 6000ms
 * 2.改为stream加Future异步任务耗时 2087ms
 */
public class CompletableFutureGetShopPrice {
    /**
     * 三个电商平台
     */
    private static List<String> shops = Arrays.asList("京东", "拼夕夕", "淘宝");

    public static void main(String[] args) {
        // 使用completableFuture获取价格
        completableFuture();

        // 使用 并行stream流获取价格
        parallelStream();

        // 使用 常规stream流获取价格
        notUseFuture();
    }

    public static void completableFuture() {
        long startTime = System.currentTimeMillis();
        List<Product> productList = shops.stream()
                .map(t ->
                        CompletableFuture.supplyAsync(() -> new Product("MySQL高性能实战", t, getPrice(t))))
                .collect(Collectors.toList())
                .stream().map(t -> t.join())
                .collect(Collectors.toList());
        long stopTime = System.currentTimeMillis();
        // 耗时：2087
        System.out.println("completableFuture-耗时：" + (stopTime - startTime));
        System.out.println(productList);
    }


    /**
     * 使用 并行stream流获取价格
     */
    public static void parallelStream() {
        long startTime = System.currentTimeMillis();
        List<Product> productList = shops.parallelStream()
                .map(t -> new Product("MySQL高性能实战", t, getPrice(t)))
                .collect(Collectors.toList());
        long stopTime = System.currentTimeMillis();
        System.out.println("parallelStream-耗时：" + (stopTime - startTime));
        System.out.println(productList);
    }

    /**
     * 不使用异步任务Future获取价格依次获取 耗时 6000ms
     */
    public static void notUseFuture() {
        long startTime = System.currentTimeMillis();
        List<Product> productList = shops.stream().map(t -> new Product("MySQL高性能实战", t, getPrice(t))).collect(Collectors.toList());
        long stopTime = System.currentTimeMillis();
        System.out.println("stream-耗时：" + (stopTime - startTime));
        System.out.println(productList);
    }

    /**
     * 查询电商平台的价格 平均每个耗时 2s
     * @return
     */
    public static double getPrice(String shopName) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (new Random().nextDouble() * (int)shopName.charAt(0)) + 50;
    }
}
