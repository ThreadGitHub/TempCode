package org.juc.threadlocal;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 软引用 内存不足时会回收
 * 设置jvm内存 -Xms10m -Xmx10m
 * @author thread
 * @date 2023/10/4 16:36
 */
public class SoftReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        SoftReference<MyObject> reference = new SoftReference<>(new MyObject());
        System.out.println(reference.get());

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println(reference.get());

        try {
            Byte[] bytes = new Byte[100 * 1024 * 1024];
        } finally {
            System.out.println(reference.get());
        }
    }
}
