package com.mysite.linkedlist.circle;


import com.mysite.linkedlist.AbstractList;
import com.mysite.linkedlist.List;

import java.io.Serializable;

/**
 * ClassName: linkedlist
 * Package: com.mysite
 * Description
 *  单向循环链表
 * @Author zhl
 * @Create 2023/12/13 20:01
 * version 1.0
 */
public class SingleCircleLinkedList<E> extends AbstractList<E> implements Serializable, List<E> {
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
        rangeCheckForAdd(index);

        //如果是第一个位置
        if (index == 0){
            Node<E> newFirst = new Node<>(element,first);
            //获取最后一个节点
            Node<E> lastNode = (size == 0) ? newFirst : node(size - 1);
            lastNode.next = newFirst;
            first = newFirst;
            size ++;
        }else {
            //获取index前一个节点
            Node<E> prev = node(index - 1);
            //将prev的下一个节点next指向之前prev.next处的节点
            prev.next  = new Node<>(element,prev.next);
            size ++;
        }

     }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null){
            for (int index = 0; index < size; index++) {
                if (node.element == null){
                    return index;
                }
                node = node.next;
            }
        }else {
            for (int index = 0; index < size; index++) {
                if (element.equals(node.element)){
                    return index;
                }
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
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
        Node<E> oldNode = node(index);
        E oldEle = oldNode.element;

        //如果删除第一个节点
        if (index == 0){
            if (size == 1){
                first = null;
            }else {
                //获取最后一个节点 -----> 改变first之前获取最后一个节点
                Node<E> lastNode = node(size - 1);
                first = oldNode.next;
                //指向更改后的first节点
                lastNode.next = first;
            }
            size --;
        }else {
            //取出删除前上一个节点
            Node<E> prev = node(index - 1);
            //将上一个节点的next指向要删除节点的下一个节点
            prev.next = oldNode.next;
            size --;
        }

        return oldEle;
    }

    @Override
    public boolean remove(E element) {
        int destIndex = indexOf(element);

        if (destIndex == -1)
            return false;

        remove(destIndex);
        return true;
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != 0){
                sb.append(", ");
            }
            sb.append(node(i).element);
        }
        sb.append("]");

        return sb.toString();
    }
}
