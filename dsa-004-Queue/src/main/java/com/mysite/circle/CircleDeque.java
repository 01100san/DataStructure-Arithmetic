package com.mysite.circle;

import com.mysite.list.LinkedList;
import com.mysite.list.List;

/**
 * ClassName: Deque
 * Package: com.mysite.queue
 * Description
 *  循环双端队列
 * @Author zhl
 * @Create 2023/12/19 19:58
 * version 1.0
 */
public class CircleDeque<E> {
    private int front;  //记录队头元素的下标
    private int size;
    private E[] elements;
    private final int DEFAULT_CAPACITY = 10;

    public CircleDeque(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }
    /**
     * 从队尾入队
     * @param element
     */
    public void enQueueRear(E element){
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size ++;
    }

    /**
     * 从队头出队
     * @return
     */
    public E deQueueFront(){
        E frontEle = elements[front];
        elements[front] = null;
        front = index(1);   //将原来的front处的位置向后移动一位
        size --;
        return frontEle;
    }

    /**
     * 从队头入队
     * @param element 入队元素
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);

        front = index(-1);  //为新的front赋值
        elements[front] = element;
        size ++;
    }

    /**
     * 从队尾出队
     * @return
     */
    public E deQueueRear(){
        int rearIndex = index(size - 1);
        E rearEle = elements[rearIndex];
        elements[rearIndex] = null;
        size --;
        return rearEle;
    }

    /**
     * 返回队头元素
     * @return
     */
    public E front(){
        return elements[front];
    }

    /**
     * 返回队尾元素
     * @return
     */
    public E rear(){
        return elements[index(size - 1)];
    }
    //想要使用循环的特性，通过取模获取下标
    private int index(int index){
        index += front;
        if (index < 0){ //如果在front=0的位置添加元素，将front位置的元素放在尾部
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }
    /**
     * 数组满了后，扩容为原来的1.5倍
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        //如果数组长度 >= size + 1 不扩容，否则扩容
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newEles = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            //newEles[i] = elements[(i + front) % elements.length];
            newEles[i] = elements[index(i)];
        }
        elements = newEles;
        //重置front
        front = 0;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.length; i++){
            if (i != 0) sb.append(", ");
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}
