package org.juc.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * 强引用
 * @author thread
 * @date 2023/10/4 16:17
 */
public class ReferenceDemo {
    static MyObject myObject = new MyObject();

    public static void main(String[] args) throws Throwable {
        myObject = null;
        System.gc();
        TimeUnit.SECONDS.sleep(20);
    }
}
