package com.mysite.list;

/**
 * ClassName: AbstractList
 * Package: com.mysite.linkedlist
 * Description
 *  抽取出ArrayList和LinkedList的公共代码
 * @Author zhl
 * @Create 2023/12/13 20:38
 * version 1.0
 */
public abstract class AbstractList<E> implements List<E> {
    protected int size;
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
     * 是否包含某元素
     * @param element 需要查找的元素
     * @return true | false
     */
    public boolean contains(E element){
        return indexOf(element) != -1;
    }
    /**
     * 向集合末添加元素
     * @param element
     */
    public void add(E element) {
        add(size,element);
    }
    protected void rangeCheckForAdd(int index){
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " + size);
    }
    protected void rangeCheck(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " + size);
    }
}
