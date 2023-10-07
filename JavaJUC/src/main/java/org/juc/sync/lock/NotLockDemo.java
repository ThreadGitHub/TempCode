package org.juc.sync.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 无锁对象状态
 * @author thread
 * @date 2023/10/5 17:14
 */

/**
 * hashcode2进制：1101101011011110110111000101000
 * # WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 28 6e 6f (00000001 00101000 01101110 01101111) (1869490177)
 *       4     4        (object header)                           6d 00 00 00 (01101101 00000000 00000000 00000000) (109)
 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * 数据查看规则：8位从左往右看  整体数据倒叙 最后8位为开始的8位从后往前看
 * markwork是8字节 8*8=64个bit位
 * 前25位为空 + 31位的hashcode值 + 1位为空 + 4位的分代年龄 + 1位的偏向锁位 + 2位锁标志位
 */
public class NotLockDemo {
    public static void main(String[] args) {
        Object obj = new Object();

        int hashcode = obj.hashCode();
        System.out.println("hashcode2进制：" + Integer.toBinaryString(hashcode));

        String printable = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(printable);
    }
}
