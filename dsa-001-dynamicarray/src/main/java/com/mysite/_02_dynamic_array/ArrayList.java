package com.mysite._02_dynamic_array;

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
public class ArrayList <E> implements Serializable {
    //数组中元素的数量
    private int size;
    //设置默认的数组容量默认长度为10
    private static final int DEFAULT_CAPACITY = 10;
    //声明动态数组
    private Object[] elements;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayList(int capacity){
        if (capacity < 0) throw new IndexOutOfBoundsException("数组容量初始化错误："+ capacity);
        //如果容量小于默认值，设为默认容量的大小，否则为指定的容量大小
        capacity = Math.max(capacity,DEFAULT_CAPACITY);
        elements = new Object[capacity];
    }

    /**
     * 元素的数量
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
       return size == 0;
    }

    /**
     * 获取指定下标的值
     * @param index
     * @return
     */
    public Object get(int index){
        rangeCheck(index);

        return elements[index];
    }

    /**
     * 设置下标为index 处的值
     * @param index 需要替换的下标
     * @param element 需要替换的元素
     * @return 替换前的值 oldVal
     */
    public Object set(int index,Object element){
        rangeCheck(index);

        Object oldVal = elements[index];
        elements[index] = element;
        return oldVal;
    }

    /**
     * 根据元素查找数组中出现的第一个下标
     * @param element 需要查找的元素
     * @return 下标 | -1
     */
    public int indexOf(Object element){
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element))
                return i;
        }
        return -1;
    }

    /**
     * 是否包含某元素
     * @param element 需要查找的元素
     * @return true | false
     */
    public boolean contains(Object element){
        return indexOf(element) != -1;
    }

    /**
     * 清除数组中所有元素
     */
    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 向数组末中动态添加元素
     * @param element 添加的元素
     */
    public void add(Object element){
        //判断是否需要扩容
        ensureCapacity(size + 1);

        elements[size++] = element;
    }

    /**
     * 按照指定的索引向数组中添加元素
     * @param index 元素添加的指定位置
     * @param element 添加的指定元素
     */
    public void add(int index, Object element){
        rangeCheckForEq(index);

        //判断是否需要扩容
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
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

    public void rangeCheckForEq(int index){
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " + size);
    }

    public void rangeCheck(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " + size);
    }

    /**
     * 按下标删除数组中的元素
     * @param index 想要删除的数组下标
     * @return 根据下标删除的数组元素
     */
    public Object remove(int index){
        rangeCheck(index);

        Object oldEle = elements[index];
        for (int i = index + 1; i <= size - 1; i++) {
            elements[i - 1] = elements[i];
        }
        size --;
        return oldEle;
    }

    /**
     * 按照元素删除数组中出现的第一个元素
     * @param element 想要删除的元素
     * @return 返回删除元素的下标
     */
    public int remove(Object element){
        //根据element查找对应的索引值
        int destIndex = indexOf(element);

        if (destIndex == -1)
            throw new IndexOutOfBoundsException("未找到指定元素");

        for (int i = destIndex + 1; i <= size - 1; i++) {
            elements[i - 1] = elements[i];
        }
        size --;
        return destIndex;
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
