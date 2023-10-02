package org.juc.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * UnSafe 获取字段在主内存的偏移量
 * @author thread
 * @date 2023/10/2 14:17
 */
public class UnSafeValueOffsetDemo {
    private volatile int value = 999;
    private volatile int value2 = 100;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafe.get(null);

        // 获取字段在主内存的偏移量
        long offset = unsafe.objectFieldOffset(UnSafeValueOffsetDemo.class.getDeclaredField("value"));
        System.out.println(offset);

        long offset2 = unsafe.objectFieldOffset(UnSafeValueOffsetDemo.class.getDeclaredField("value2"));
        System.out.println(offset2);
    }
}
