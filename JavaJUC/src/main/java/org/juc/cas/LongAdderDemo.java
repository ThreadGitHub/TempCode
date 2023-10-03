package org.juc.cas;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 统计累加 LongAdder demo
 * @author thread
 * @date 2023/10/3 15:47
 */
public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.add(1);
        longAdder.add(2);
        longAdder.increment();
        long sum = longAdder.sum();
        System.out.println(sum);

        LongAccumulator longAccumulator = new LongAccumulator((left,right)->left + right, 1);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(2);
        longAccumulator.accumulate(3);
        long result = longAccumulator.get();
        System.out.println(result);
    }
}
