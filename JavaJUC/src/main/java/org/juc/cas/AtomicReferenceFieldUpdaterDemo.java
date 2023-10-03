package org.juc.cas;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * AtomicReferenceFieldUpdater 引用类型修改字段原子类
 * @author thread
 * @date 2023/10/2 23:51
 */
public class AtomicReferenceFieldUpdaterDemo {
    private volatile Boolean flag = Boolean.FALSE;

    private static AtomicReferenceFieldUpdater<AtomicReferenceFieldUpdaterDemo, Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterDemo.class, Boolean.class, "flag");

    public static void main(String[] args) {
        AtomicReferenceFieldUpdaterDemo demo = new AtomicReferenceFieldUpdaterDemo();
        referenceFieldUpdater.getAndSet(demo, true);
        System.out.println(demo.flag);
    }
}
