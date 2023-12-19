package com.mysite.queue;

import com.mysite.list.LinkedList;
import com.mysite.list.List;

/**
 * ClassName: Deque
 * Package: com.mysite.queue
 * Description
 *  双向队列
 * @Author zhl
 * @Create 2023/12/19 19:58
 * version 1.0
 */
public class Deque<E> {
    private List<E> list = new LinkedList<>();
    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 从队尾入队
     * @param element
     */
    public void enQueueRear(E element){
        list.add(element);  //在双向链表最后添加元素
    }

    /**
     * 从队头出队
     * @return
     */
    public E deQueueFront(){
        return list.remove(0);
    }

    /**
     * 从队头入队
     * @param element 入队元素
     */
    public void enQueueFront(E element) {
        list.add(0,element);
    }

    /**
     * 从队尾出队
     * @return
     */
    public E deQueueRear(){
        return list.remove(list.size() - 1);
    }

    /**
     * 返回队头元素
     * @return
     */
    public E front(){
        return list.get(0);
    }

    /**
     * 返回队尾元素
     * @return
     */
    public E rear(){
        return list.get(list.size() - 1);
    }
}
