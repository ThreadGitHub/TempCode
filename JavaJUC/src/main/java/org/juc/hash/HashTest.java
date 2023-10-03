package org.juc.hash;

import java.util.Set;

/**
 * 测试hash算法
 * @author thread
 * @date 2023/10/4 00:02
 */
public class HashTest {
    public static void main(String[] args) {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("my", 123);
        hashMap.put("my", 456);
        for (int i = 0; i <100; i++) {
            hashMap.put("key-" + i, i);
        }

        Set<String> keys = hashMap.getKeys();
        for (String key : keys) {
            System.out.println(hashMap.get(key));
        }


        MyHashMap<HashTest, String> referenceMap = new MyHashMap<>();
        HashTest hashTest = new HashTest();
        referenceMap.put(hashTest, "第一个对象");
        referenceMap.put(new HashTest(), "第二个对象");
        System.out.println(referenceMap);
        System.out.println(referenceMap.get(hashTest));
    }
}
