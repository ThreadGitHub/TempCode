package org.juc.aqs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己实现ReentrantLock 太难了家人们 公平锁未实现不管用
 * @author thread
 * @date 2023/10/15 20:54
 */
public class MyReentrantLock {
    /**
     * 是否公平
     */
    private boolean fair = false;

     /**
     * 控制是否加锁 和 锁重入次数
     */
    private AtomicInteger state = new AtomicInteger(0);

    /**
     * 持锁线程
     */
    private volatile Thread ownerThread;

    /**
     * 头节点
     */
    private volatile Node head;

    /**
     * 尾节点
     */
    private volatile Node tail;

    static class Node {
        /**
         * 前一个节点
         */
        private Node prev;

        /**
         * 后一个节点
         */
        private Node next;

        private Thread thread;

        /**
         * 监听字段
         * 1 表示当前节点的next后面有下一个节点需要唤醒
         * 0 没有下一个节点需要唤醒
         * -1 已经唤醒过next节点了
         */
        private AtomicInteger waitStatus = new AtomicInteger(0);

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", thread=" + thread +
                    ", waitStatus=" + waitStatus +
                    '}';
        }
    }

    public MyReentrantLock(boolean fair) {
        this.fair = fair;
    }

    public MyReentrantLock() {
    }

    /**
     * 实现公平锁 貌似不管用不管了。。。
     * @return
     */
    public boolean hasQueuedPredecessors() {
        if (fair) {
            Node s;
            return head != tail &&
                    ((s = head.next) != null || s.thread == Thread.currentThread());
        } else {
            return true;
        }
    }

    /**
     * 尝试抢锁
     * @return
     */
    public boolean tryAcquire() {
        if (hasQueuedPredecessors() && state.compareAndSet(0, 1)) {
            return true;
        }
        return false;
    }

    /**
     * 初始化队列 这里为什么用synchronized其实也可以用
     */
    private void initQueue() {
        // 队列不存在
        if (tail == null) {
            synchronized (MyReentrantLock.class) {
                if (tail == null) {
                    // 创建虚节点
                    Node node = new Node();
                    head = node;
                    tail = node;
                }
            }
        }
    }

    /**
     * 加锁
     */
    public void lock() {
        // 尝试抢占锁
        if (tryAcquire()) {
            // 设置当前线程为持有锁线程
            this.ownerThread = Thread.currentThread();
        } else {
            // 初始化队列
            initQueue();

            // 创建当前线程的Node节点
            Node node = new Node();
            node.thread = Thread.currentThread();

            // 抢占失败入队列
            while (true) {
                // 尝试抢锁
                if (tryAcquire()) {
                    // 设置当前线程为持有锁线程
                    this.ownerThread = Thread.currentThread();
                    return;
                }

                // cas入队
                if (tail.waitStatus.compareAndSet(0, 2)) {
                    // 入队
                    node.prev = tail;
                    // 追加节点
                    tail.next = node;

                    // 真正把节点拼完以后 才把状态改为1可唤醒状态
                    tail.waitStatus.compareAndSet(2, 1);

                    // 尾节点移动
                    tail = node;

                    // 入队以后 和 睡觉之前 这段时间 再尝试抢锁
                    // node.prev == head
                    // 这里为什么是第二个节点才能抢锁成功因为并发的过程可能会唤醒多个线程要保证按顺序抢锁不然会出现数据混乱
                    if (node.prev == head && tryAcquire()) {
                        // 自己节点变为新的头节点
                        node.thread = null;
                        node.prev = null;
                        // 设置当前线程为持有锁线程
                        this.ownerThread = Thread.currentThread();
                        // 替换头节点
                        head = node;
                        return;
                    }
                    break;
                }
            }

            // 开始睡觉
            LockSupport.park(Thread.currentThread());

            // 醒了后循环抢锁
            for (;;) {
                // 唤醒后再次抢锁
                if (node.prev == head && tryAcquire()) {
                    // 自己节点变为新的头节点
                    node.thread = null;
                    node.prev = null;
                    // 设置当前线程为持有锁线程
                    this.ownerThread = Thread.currentThread();
                    // 替换头节点
                    head = node;
                    break;
                }
            }
        }
    }

    /**
     * 解锁
     */
    public void unlock() {
//        System.out.println(Thread.currentThread() + " " + head);
        // 如果减到0就解锁成功
        if (ownerThread == Thread.currentThread()) {
            //解锁处理
            if ((state.get() -1) == 0) {
                // 持有线程设为null
                ownerThread = null;

                // 唤醒next线程继续抢锁
                if (head != null && head.waitStatus.compareAndSet(1, -1)) {
                    // 唤醒线程
                    LockSupport.unpark(head.next.thread);
                }
            }

            // 解锁 state-1 这里一定是最后再放锁 不然线程会从缝隙进来
            state.decrementAndGet();
        }
    }
}