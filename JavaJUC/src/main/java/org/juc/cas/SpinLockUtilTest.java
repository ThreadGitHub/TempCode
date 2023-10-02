package org.juc.cas;

import java.util.concurrent.CountDownLatch;

/**
 * 自旋锁使用测试
 * @author thread
 * @date 2023/10/2 15:47
 */
public class SpinLockUtilTest {
    SpinLockUtil spinLockUtil = new SpinLockUtil();

    private int num = 0;
    public void addNum() {
        // 加锁
        try {
            spinLockUtil.lock();
            num++;
        } finally {
            spinLockUtil.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockUtilTest resource = new SpinLockUtilTest();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                for (int j = 0; j < 1000; j++) {
                    resource.addNum();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(resource.num);
    }
}
