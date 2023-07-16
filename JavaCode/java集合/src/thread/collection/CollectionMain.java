package thread.collection;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CollectionMain {
    private static Object obj = new Object();

    public static void main(String[] args) {
        String str = "abc";
        int i = str.hashCode();
        i = i ^ i >>> 16;
        System.out.println(Integer.toBinaryString(15));
        System.out.println(Integer.toBinaryString(i));
    }

    private static void printStr(String str) {
        System.out.println(String.class.getName() + "@" + Integer.toHexString(System.identityHashCode(str)) + "\t" + str);
    }

    private static void copyOnWriteArrayList() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("123");
        copyOnWriteArrayList.add("435");
        System.out.println(copyOnWriteArrayList);
    }

    private static void vector() {
        ReentrantLock reentrantLock = new ReentrantLock();
        Vector<String> vector = new Vector();
//        List vector = new ArrayList(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue(1));
        CountDownLatch countDownLatch = new CountDownLatch(20);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
//                reentrantLock.lock();
//                synchronized (obj) {
                vector.add(Thread.currentThread().getName() + ": " + finalI);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
//                }
//                reentrantLock.unlock();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(vector);
        System.out.println("用时：" + (System.currentTimeMillis() - time));
    }

    private void arrayList() {
        List list = new ArrayList();
        for (int i = 0; i < 11; i++) {
            if (i == 10) {
                System.out.println("i: " + i);
            }
            list.add(i);
        }
        System.out.println(list);

        list.remove(2);

        System.out.println(10 >> 1);

        int[] array = {
                1, 2, 3, 4, 5
        };
        System.out.println("移动之前：" + Arrays.stream(array).mapToObj(t -> String.valueOf(t)).collect(Collectors.joining(",")));
        int index = 1;
        int copySize = array.length - (index + 1);
        System.arraycopy(array, index + 1, array, index, copySize);
        array[array.length - 1] = 0;
        System.out.println("移动之后：" + Arrays.stream(array).mapToObj(t -> String.valueOf(t)).collect(Collectors.joining(",")));


//        int oldCapacity = 1796357452;
//        int capacity = oldCapacity + (oldCapacity >> 1);
//        int minCapacity = oldCapacity + 1;
//        System.out.println("capacity: " + capacity);
//        System.out.println("minCapacity: " + minCapacity);
//        System.out.println(capacity - minCapacity);
    }

    private void linkedList() {
        LinkedList<String> linkedList = new LinkedList<>();
        String str = "AJGKDSAJGLKDSJAGKLDSJALGJDSLAJGLDKSA";
        for (int i = 0; i < 10000 * 10; i++) {
            linkedList.add(str);
        }
        System.out.println(linkedList);
    }
}