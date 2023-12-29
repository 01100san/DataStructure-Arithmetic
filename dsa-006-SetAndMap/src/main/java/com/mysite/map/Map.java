package com.mysite.map;

/**
 * ClassName: Map
 * Package: com.mysite.map
 * Description
 *
 * @Author zhl
 * @Create 2023/12/28 20:32
 * version 1.0
 */
public interface Map<K,V> {
    int size();
    boolean isEmpty();
    void clear();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    void traversal(Visitor<K, V> visitor);
    public static abstract class Visitor<K, V>{
        boolean stop;
        public abstract boolean visit(K key, V value);
    }
}
