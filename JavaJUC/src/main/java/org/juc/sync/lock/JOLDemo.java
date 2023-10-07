package org.juc.sync.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * jol 工具简单实用
 * @author thread
 * @date 2023/10/5 15:36
 */
public class JOLDemo {
   /* private String name;

    private byte by;*/

    public static void main(String[] args) throws InterruptedException {
        JOLDemo jolDemo = new JOLDemo();
        String printable = ClassLayout.parseInstance(jolDemo).toPrintable();
        System.out.println(printable);

//        Thread.currentThread().join();
    }
}
