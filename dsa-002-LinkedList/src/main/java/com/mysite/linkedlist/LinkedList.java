package com.mysite.linkedlist;


import java.io.Serializable;
import java.util.TooManyListenersException;

/**
 * ClassName: linkedlist
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/13 20:01
 * version 1.0
 */
public class LinkedList<E> extends AbstractList<E> implements Serializable, List<E> {
    private Node<E> first;

    /**
     * 将第一个节点设为null
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    /**
     * 在指定的节点处添加元素（节点前）
     * @param index 指定的索引
     * @param element 需要添加的元素
     */
    @Override
    public void add(int index, E element) {
        rangeCheck(index);

        //取出第一个节点
        Node<E> node = first;
        //如果是第一个位置
        if (index == 0){
            node = new Node<>(element,first);
        }


        //如果不是第一个节点前的位置
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        //获取前一个节点
        Node<E> tempNode = node(index);
        Node<E> newNode = new Node<>(element,node);

        tempNode.next = newNode;

    }

    @Override
    public int indexOf(E element) {
        //如果元素值和第一个节点的元素相等，直接返回第一个节点的索引
        if (element.equals(first.element)) {
            return 0;
        }
        //取出第一个节点的下一个节点
        Node<E> node = first.next;
        for (int index = 1; index < size; index++) {
            if (element.equals(node.element)){
                return index;
            }
            node = node.next;
        }

        return -1;
    }

    @Override
    public E get(int index) {
        //得到index前的一个节点
        Node<E> node = node(index);
        return node.element;
    }

    @Override
    public E set(int index, E element) {
        //取出原来的index对应的node值
        Node<E> node = node(index);
        E oldEle = node.element;
        node.element = element;
        return oldEle;
    }

    @Override
    public E remove(int index) {
        //待删除的节点
        /*Node<E> node = node(index);
        E element = node.element;
        Node<E> next = node.next;

        return element;*/
        return null;
    }

    /**
     * 根据索引的位置获取节点
     * @param index 需要查找索引的位置
     * @return 返回当前索引的节点
     */
    public Node<E> node(int index){
        rangeCheck(index);
        //遍历每一个节点
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
