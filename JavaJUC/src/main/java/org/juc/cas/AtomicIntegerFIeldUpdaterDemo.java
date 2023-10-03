package org.juc.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFIeldUpdater 对int类型的字段进行原子更新
 * @author thread
 * @date 2023/10/2 20:19
 */
public class AtomicIntegerFIeldUpdaterDemo {
    static AtomicIntegerFieldUpdater<People> fieldUpdater = AtomicIntegerFieldUpdater
            .newUpdater(People.class, "money");

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        People people = new People();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    // 递增1
                    fieldUpdater.getAndIncrement(people);
//                    people.addMoney();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(people.money);
    }
}

class People {
    volatile int money;

    public void addMoney() {
        money++;
    }
}