package com.mysite.queue;

import com.mysite.heap.BinaryHeap;

import java.util.Comparator;

/**
 * ClassName: PriorityQueue
 * Package: com.mysite.queue
 * Description
 *  优先级队列 -> 二叉堆实现
 * @Author zhl
 * @Create 2024/1/1 21:42
 * version 1.0
 */
public class PriorityQueue<E> {
    private BinaryHeap<E> heap;
    public PriorityQueue(Comparator<E> comparator){
        heap = new BinaryHeap<>(comparator);
    }
    public PriorityQueue(){
        this(null);
    }
    public int size(){
        return heap.size();
    }
    public boolean isEmpty(){
        return heap.isEmpty();
    }
    //入队
    public void enQueue(E element){
        heap.add(element);
    }
    //让优先级最高的出队
    public E deQueue(){
        return heap.remove();
    }
    //获取堆顶元素
    public E front(){
        return heap.get();
    }
    public void clear(){
        heap.clear();
    }
}
