package org.juc.threadlocal;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用
 * 只要垃圾回收一启动 不管内存如何 都会回收
 * @author thread
 * @date 2023/10/4 16:44
 */
public class WeakReferenceDemo {
    static MyObject myObject = new MyObject();
    Entry entry = new Entry(myObject, "value");

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize: " + this.entry.get());
    }

    static class Entry extends WeakReference<MyObject>{
        private String value;

        public Entry(MyObject referent, String value) {
            super(referent);
            this.value = value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WeakReferenceDemo demo = new WeakReferenceDemo();

        MyObject obj = new MyObject();
        WeakReference<MyObject> weakReference = new WeakReference<>(obj);
        System.out.println("weakReferenceA: " + weakReference.get());

        obj = null;

        System.gc();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("weakReferenceB: " + weakReference.get());
        System.out.println("entryA: " + demo.entry.get());

        demo = null;

        System.gc();
        TimeUnit.SECONDS.sleep(1);

        Thread.currentThread().join();
    }
}
