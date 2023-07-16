package thread.collection;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 斐波那契算法
 *
 * @author xpy
 * @date 2022/07/26
 */
public class IFibonacci {

    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    private final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度
    private final int ARR_LENGTH = 128;

    private Map<Long, int[]> intMap = new ConcurrentHashMap<>();

    /**
     * 斐波那契（Fibonacci）散列法，计算哈希索引下标值
     *
     * @param val 值
     * @return 索引
     */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (ARR_LENGTH - 1);
    }

    public static void main(String[] args) {
        IFibonacci iFibonacci = new IFibonacci();
        int[] arr = iFibonacci.intMap.computeIfAbsent(1L, k -> new int[iFibonacci.ARR_LENGTH]);
        // 将0-100随机打乱
        for (int i = 1; i <= 100; i++) {
            arr[iFibonacci.hashIdx(i)] = i;
        }
        // 遍历
        System.out.println(Arrays.toString(iFibonacci.intMap.get(1L)));
    }
}