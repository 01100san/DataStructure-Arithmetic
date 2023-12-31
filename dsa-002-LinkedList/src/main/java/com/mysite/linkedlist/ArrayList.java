package com.mysite.linkedlist;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ClassName: ArrayList
 * Package: com.mysite._02_dynamic_array
 * Description
 *  自定义一个数组
 * @Author zhl
 * @Create 2023/12/11 22:50
 * version 1.0
 */
public class ArrayList<E> extends AbstractList<E> implements Serializable,List<E> {
    //数组中元素的数量
    /*private int size;*/
    //设置默认的数组容量默认长度为10
    private static final int DEFAULT_CAPACITY = 10;
    //声明动态数组
    private E[] elements;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayList(int capacity){
        if (capacity < 0) throw new IndexOutOfBoundsException("数组容量初始化错误："+ capacity);
        //如果容量小于默认值，设为默认容量的大小，否则为指定的容量大小
        capacity = Math.max(capacity,DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public E get(int index){    //O(1)
        rangeCheck(index);

        return elements[index];
    }

    public E set(int index,E element){  //O(1)
        rangeCheck(index);

        E oldVal = elements[index];
        elements[index] = element;
        return oldVal;
    }

    public int indexOf(E element){
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null)
                    return i;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element))
                    return i;
            }
        }
        return -1;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public void add(E element){
        /**
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(1)
         * 均摊：O(1)  => 经过连续的多次复杂度比较低的情况下，出现个别复杂度比较高的情况
         */
        //判断是否需要扩容
        ensureCapacity(size + 1);

        elements[size++] = element;
    }

    public void add(int index, E element){
        /**
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheckForAdd(index);

        //判断是否需要扩容
        ensureCapacity(size + 1);

        System.arraycopy(elements,index,elements,index + 1,
                size - index);

        /*for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }*/
        elements[index] = element;
        size++;
    }

    /**
     * 数组满了后，扩容为原来的1.5倍
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        //如果数组长度 >= size + 1 不扩容，否则扩容
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements,newCapacity);
    }

    public E remove(int index){
        /**
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheck(index);

        E oldEle = elements[index];

        //设置源数组移动的位置 numRemoved
        int numMoved = size - index - 1;
        System.arraycopy(elements, index + 1,elements,index,numMoved);

        //将index后的元素向前移动一位
        /*for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }*/

        elements[--size] = null;

        //判断是否需要缩容
        trim();

        return oldEle;
    }

    public boolean remove(E element){
        //根据element查找对应的索引值
        int destIndex = indexOf(element);

        if (destIndex == -1)
            return false;

        //设置源数组移动的位置 numRemoved
        int numMoved = size - destIndex - 1;
        System.arraycopy(elements, destIndex + 1,elements,destIndex,numMoved);

        /*for (int i = destIndex + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }*/

        elements[--size] = null;

        //判断是否需要缩容
        trim();

        return true;
    }

    private void trim() {
        int oldCapacity = elements.length;
        if (size >= (elements.length >> 1) || oldCapacity <= DEFAULT_CAPACITY)
            return;

        //当剩余空间大于总空间的一半时，进行缩容,缩容为原来的0.75倍
        int newCapacity = oldCapacity - (oldCapacity >> 2);
        elements = Arrays.copyOf(elements,newCapacity);

        System.out.println(oldCapacity + "缩容为：" + newCapacity);
    }

    /**
     * 遍历数组中的元素
     * @return
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++){
            if (i != 0) sb.append(", ");
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
