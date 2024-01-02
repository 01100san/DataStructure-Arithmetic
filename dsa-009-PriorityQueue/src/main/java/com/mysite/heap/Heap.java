package com.mysite.heap;

/**
 * ClassName: Heap
 * Package: com.mysite.heap
 * Description
 *
 * @Author zhl
 * @Create 2024/1/1 14:14
 * version 1.0
 */
public interface Heap<E> {
    int size(); // 元素的数量
    boolean isEmpty(); // 是否为空
    void clear();//清空
    void add(E element); // 添加元素
    E get();// 获得堆顶元素
    E remove(); //删除堆顶元素
    E replace(E element); //删除堆顶元素,同时插入新的元素
}
