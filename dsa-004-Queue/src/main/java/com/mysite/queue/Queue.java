package com.mysite.queue;

import com.mysite.list.LinkedList;
import com.mysite.list.List;

/**
 * ClassName: Queue
 * Package: com.mysite.queue
 * Description
 *  队列
 * @Author zhl
 * @Create 2023/12/18 22:25
 * version 1.0
 */
public class Queue<E> {
    private List<E> list = new LinkedList<>();
    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入队
     */
    public void enQueue(E element){
        list.add(element);
    }

    /**
     * 出队
     * @return
     */
    public E deQueue(){
        return list.remove(0);
    }

    /**
     * 获取队列的头元素
     * @return
     */
    public E front(){
        return list.get(0);
    }
    public void clear(){
        list.clear();
    }
}



