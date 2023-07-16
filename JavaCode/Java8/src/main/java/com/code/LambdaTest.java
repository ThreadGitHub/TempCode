package com.code;

import com.code.defaultInterface.DefaultInterface;
import com.code.defaultInterface.DefaultInterfaceImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

public class LambdaTest {
   @Test
    public void lambdaTest() {
        BiFunction<String, String, String> biFunction = (String a, String b) -> a + b;
        String apply = biFunction.apply("ABC", "123");
        System.out.println(apply);

        MyPrint myPrint = () -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println(i);
            }
        };
        myPrint.run();

       Thread thread = new Thread(this::consumer);
       thread.start();

       AtomicInteger count = new AtomicInteger();
       List<String> list = new CopyOnWriteArrayList<>();
       list.add("A");
       list.forEach(t -> {
           list.add("F");
       });
       System.out.println(list);
   }

    /**
     * 测试Java8 default Interface 特性
     */
   @Test
   public void testDefaultInterface() {
       // 调用接口默认静态方法
       DefaultInterface.testStatic();

       // 调用接口默认的普通方法
       DefaultInterface defaultInterface = new DefaultInterfaceImpl();
       defaultInterface.test();
   }

   @Test
   public void testStream() {
       List<String> list = Arrays.asList("A", "B", "C", "D");
       String s = list.stream().findFirst().orElse(null);
       System.out.println(s);

       list.parallelStream().forEach(t-> {
           System.out.println(Thread.currentThread().getName() + "\t" + t);
       });
   }

    public void consumer() {
        System.out.println(Thread.currentThread().getName() + ": start");
    }
}
