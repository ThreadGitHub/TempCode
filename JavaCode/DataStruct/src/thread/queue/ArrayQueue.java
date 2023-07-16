package thread.queue;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 数组模拟队列
 */
public class ArrayQueue {
    /**
     * 最后入队的数据的位置
     */
    private int rear = -1;
    /**
     * 出队的数据位置
     */
    private int front = -1;
    /**
     * 队列的长度
     */
    private int maxSize = 0;
    /**
     * 存储队列数据的数组
     */
    private int[] data;

    /**
     * 初始化链表
     * @param size 链表长度
     */
    public ArrayQueue(int size) {
        maxSize = size;
        data = new int[maxSize];
    }

    /**
     * 入队
     * @param element 增加的元素
     * @throws Exception 可能发生的异常， 队列已满
     */
    public void add(int element) throws Exception {
        if (rear >= maxSize - 1) {
            throw new Exception("队列已满");
        }
        rear++;
        data[rear] = element;
    }

    /**
     * 出队
     * @return
     */
    public int get() throws Exception {
        if (rear == front) {
            throw new Exception("队列为空");
        }
        front++;
        return data[front];
    }

    /**
     * 显示队列中的所有元素
     * @throws Exception
     */
    public void showQueue() throws Exception {
        if (rear == front) {
            throw new Exception("队列为空");
        }
        String arrayStr = Arrays.stream(data).mapToObj(t -> String.valueOf(t)).collect(Collectors.joining(","));
        System.out.println("Queue: [" + arrayStr + "]");
    }
}
