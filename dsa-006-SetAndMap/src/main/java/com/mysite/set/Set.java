package com.mysite.set;

import sun.reflect.generics.visitor.Visitor;

/**
 * ClassName: Set
 * Package: com.mysite.set
 * Description
 *
 * @Author zhl
 * @Create 2023/12/28 16:00
 * version 1.0
 */
public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E>{
        boolean stop;
        public abstract boolean visit(E element);
    }
}
