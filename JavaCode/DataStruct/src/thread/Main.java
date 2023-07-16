package thread;

import thread.queue.ArrayQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        arrayQueue.add(10);
        arrayQueue.add(20);
        arrayQueue.add(30);
        arrayQueue.showQueue();

        System.out.println("出队: " + arrayQueue.get());
        arrayQueue.showQueue();
        System.out.println("出队: " + arrayQueue.get());
        arrayQueue.showQueue();
        System.out.println("出队: " + arrayQueue.get());
    }
}
