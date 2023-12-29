package com.mysite.set;

import com.mysite.map.Map;
import com.mysite.map.TreeMap;
import com.mysite.map.TreeMapp;

/**
 * ClassName: TreeSet
 * Package: com.mysite.set
 * Description
 *  TreeSet -> TreeMap
 * @Author zhl
 * @Create 2023/12/29 14:20
 * version 1.0
 */
public class TreeSet<E> implements Set<E>{
    Map<E, Object> map = new TreeMapp<>();
    //Map<E, Object> map = new TreeMap<>(); //TreeMap存在bug
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
