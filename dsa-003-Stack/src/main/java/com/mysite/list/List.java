package com.mysite.list;

/**
 * ClassName: List
 * Package: com.mysite.linkedlist
 * Description
 * @Author zhl
 * @Create 2023/12/13 20:21
 * version 1.0
 */
public interface List<E>{
    /**
     * 清除集合中所有元素
     */
    void clear();
    int size();
    boolean isEmpty();
    boolean contains(E element);
    /**
     * 向集合末尾添加元素
     * @param element 添加的元素
     */
    void add(E element);
    /**
     * 按照指定的下标向集合中添加元素
     * @param index 元素添加的指定下标
     * @param element 添加的指定元素
     */
    void add(int index, E element);
    /**
     * 按下标删除集合中的元素
     * @param index 目的删除集合下标对应的元素
     * @return 返回被删除的元素
     */
    E remove(int index);
    /**
     * 按照元素删除集合中出现的第一个元素
     * @param element 想要删除的元素 可以是null
     * @return
     */
    boolean remove(E element);
    /**
     * 根据元素查找集合中出现的第一个下标
     * @param element 需要查找的元素 可以是null
     * @return 返回集合对应的下标 或 -1
     */
    int indexOf(E element);
    /**
     * 获取指定下标的元素
     * @param index 指定下标
     * @return 返回指定下标的元素
     */
    E get(int index);
    /**
     * 更改当前下标对应的元素
     * @param index 当前下标
     * @param element 需要更改的元素
     * @return 返回更改前的元素
     */
    E set(int index, E element);
}
