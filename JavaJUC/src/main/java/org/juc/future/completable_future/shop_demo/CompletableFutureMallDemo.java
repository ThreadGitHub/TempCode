package org.juc.future.completable_future.shop_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 电商平台案例
 * @author thread
 * @date 2023/9/26 20:54
 */
public class CompletableFutureMallDemo {
    public static void main(String[] args) {
        // 串行stream流查询结果 耗时：5159
        streamSearch();

        // 并行stream 耗时：1014
        parallelStreamSearch();

        // completableFuture 耗时：1013
        completableFuture();
    }

    public static void streamSearch() {
        long start = System.currentTimeMillis();
        List<String> prizeList = netMallList.stream()
                .map(CompletableFutureMallDemo::getProduct)
                .collect(Collectors.toList());
        System.out.println(prizeList);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    public static void parallelStreamSearch() {
        long start = System.currentTimeMillis();
        List<String> prizeList = netMallList.parallelStream()
                .map(CompletableFutureMallDemo::getProduct)
                .collect(Collectors.toList());
        System.out.println(prizeList);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    public static void completableFuture() {
        long start = System.currentTimeMillis();
        List<String> prizeList = netMallList.stream()
                .map(t -> CompletableFuture.supplyAsync(() -> getProduct(t)))
                .collect(Collectors.toList())
                .stream()
                .map(t -> t.join())
                .collect(Collectors.toList());
        System.out.println(prizeList);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    // 模拟各个平台
    static List<String> netMallList = new ArrayList<String>(){{
        add("天猫");
        add("京东");
        add("苏宁");
        add("淘宝");
        add("拼多多");
    }};

    /**
     * 模拟获取各个平台获取价格的耗时
     * @param netMall
     * @return
     */
    public static String getProduct(String netMall) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double prize = new Random().nextDouble() * ((int) netMall.charAt(1));
        return String.format("商品在[%s]的价格: %s元", netMall, prize);
    }
}
