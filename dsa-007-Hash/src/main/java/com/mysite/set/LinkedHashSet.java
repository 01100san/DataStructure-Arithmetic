package com.mysite.set;

import com.mysite.map.LinkedHashMap;
import com.mysite.map.Map;

/**
 * ClassName: LinkedHashSet
 * Package: com.mysite.set
 * Description
 *  LinkedHashSet -> LinkedHashMap
 * @Author zhl
 * @Create 2024/1/1 11:00
 * version 1.0
 */
public class LinkedHashSet<E> implements Set<E> {
    private LinkedHashMap<E,Object> map = new LinkedHashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element,null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
