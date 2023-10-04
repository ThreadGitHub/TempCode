package org.juc.threadlocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用 回收后会放入引用队列中
 * @author thread
 * @date 2023/10/4 17:10
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> reference = new PhantomReference<>(new MyObject(), referenceQueue);
        System.out.println(reference.get());

        List list = new ArrayList<>();
        new Thread(()-> {
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);
                System.out.println(reference.get());

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()-> {
            while (true) {
                Reference<? extends MyObject> poll = referenceQueue.poll();
                if (poll != null) {
                    System.out.println("有虚引用进入队列了。。。。");
                    System.out.println(poll.get());
                }
            }
        }).start();
    }
}
