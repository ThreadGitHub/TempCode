package org.juc.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArray 原子数组类使用
 * @author thread
 * @date 2023/10/2 17:05
 */
public class AtomicIntegerArrayDemo {
    private static int[] array = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        // 获取下标值
        int i = atomicIntegerArray.get(0);
        System.out.println(i);

        // 获取并自增 自减
        int andIncrement = atomicIntegerArray.getAndIncrement(1);
        System.out.println(andIncrement);
        int andDecrement = atomicIntegerArray.getAndDecrement(2);
        System.out.println(andDecrement);

        // 取旧值并更新
        int andSet = atomicIntegerArray.getAndSet(1, 7);
        System.out.println(andSet);

        // 遍历数组
        System.out.println(atomicIntegerArray);
        for (int j = 0; j < atomicIntegerArray.length(); j++) {
            int num = atomicIntegerArray.get(j);
            System.out.println(num);
        }
    }
}
