package org.juc.hash;

import java.util.*;

/**
 * Hash算法测试
 * @author thread
 * @date 2023/10/3 23:02
 */
public class MyHashMap<K,V> {
    private Node<K,V>[] array = new Node[16];
    private Set<K> keys = new LinkedHashSet<>();

    public Set<K> getKeys() {
        return keys;
    }

    private int getIndex(K key) {
        int hash = key.hashCode();
        return hash & array.length - 1;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K,V> node;
        keys.add(key);
        // 槽位为空
        if ((node = array[index]) == null) {
            array[index] = new Node<K, V>(key, value);
        } else if ((node.nextNode) == null) {
            // 槽位只有一个元素时
            if (node.key.hashCode() != key.hashCode()) {
                node.nextNode = new Node<K, V>(key, value);
            } else {
                node.value = value;
            }
        } else {
            // 遍历链表
            do {
                if (node.key.hashCode() == key.hashCode()) {
                    node.value = value;
                    return;
                } else if (node.nextNode == null) {
                    node.nextNode = new Node<K, V>(key, value);
                }
            } while ((node = node.nextNode) != null);
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K,V> node;
        if ((node = array[index]) == null) {
            return null;
        } else if (node.nextNode == null) {
            return node.value;
        } else {
            do {
                if (node.key.hashCode() == key.hashCode()) {
                    return node.value;
                }
            } while ((node = node.nextNode) != null);
            return null;
        }
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    static class Node<K,V> {
        private K key;
        private V value;

        private Node<K,V> nextNode;

        public Node(K key, V v) {
            this.key = key;
            this.value = v;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
