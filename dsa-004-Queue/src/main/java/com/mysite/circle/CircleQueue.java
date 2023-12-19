package com.mysite.circle;

import java.awt.*;
import java.util.Arrays;

/**
 * ClassName: CircleQueue
 * Package: com.mysite.circle
 * Description
 *  循环队列
 * @Author zhl
 * @Create 2023/12/19 20:35
 * version 1.0
 */
public class CircleQueue<E> {
    private int front;  //记录队头元素的下标
    private int size;
    private E[] elements;
    private final int DEFAULT_CAPACITY = 10;

    public CircleQueue(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    //入队
    public void enQueue(E element){
        ensureCapacity(size + 1);

        //elements[(front + size) % elements.length] = element;
        elements[index(size)] = element;
        size ++;
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
        /*elements = Arrays.copyOf(elements,newCapacity);
        //将从front开始到size结束的元素(elements[j])，放到扩容的位置(elements[i])
        for (int i = size,j = size - front; i < newCapacity; i++, j++){
            elements[i] = elements[j];
        }
        for (int i = size - front; i < size; i++) {
            elements[i] = null;
        }
        //修改队头元素的下标
        front = newCapacity - front;*/
    }

    //出队
    public E deQueue(){
        E frontEle = elements[front];
        elements[front] = null;
        //front = (front + 1) % elements.length;
        front = index(1);
        size --;
        return frontEle;
    }
    //想要使用循环的特性，通过取模获取下标
    private int index(int index){
        return (front + index) % elements.length;
    }

    public E front(){
        return elements[front];
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


