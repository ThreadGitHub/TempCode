package org.juc.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference 通过版本解决ABA问题
 * @author thread
 * @date 2023/10/2 16:29
 */
public class AtomicStampedReferenceDemo {
    private static AtomicStampedReference<User> reference = new AtomicStampedReference<>(new User("zhangsan", 19), 1);

    public static void main(String[] args) throws InterruptedException {
        int stamp = reference.getStamp();
        User oldUser = reference.getReference();
        reference.compareAndSet(oldUser, new User("lisi", 20), stamp, stamp+1);

        stamp = reference.getStamp();
        oldUser = reference.getReference();
        reference.compareAndSet(oldUser, new User("zhangsan", 19), stamp, stamp+1);

        // 此时 zhangsan的stamp的值已经变为了3不再是1Ø
        System.out.println(reference.getReference() + "\t" + reference.getStamp());
    }
}
