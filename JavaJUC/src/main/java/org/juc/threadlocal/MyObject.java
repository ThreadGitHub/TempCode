package org.juc.threadlocal;

/**
 * @author
 * @date 2023/10/4 16:16
 */
public class MyObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyObject 呜呜呜我被回收了家人们。。。");
        super.finalize();
    }
}
