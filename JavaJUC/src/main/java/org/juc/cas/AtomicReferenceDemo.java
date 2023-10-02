package org.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用类的实现demo
 * @author thread
 * @date 2023/10/2 15:19
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User userA = new User("zhangsan", 19);
        User userB = new User("lisi", 17);

        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(userA);

        // 尝试修改为UserB
        reference.compareAndSet(userA, userB);

        System.out.println(reference.get());
    }
}
@Getter
@Setter
@AllArgsConstructor
@ToString
class User {
    private String name;
    private int age;
}
