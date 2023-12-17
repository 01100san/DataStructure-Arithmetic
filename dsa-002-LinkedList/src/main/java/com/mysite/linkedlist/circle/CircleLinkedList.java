package com.mysite.linkedlist.circle;


import com.mysite.linkedlist.AbstractList;
import com.mysite.linkedlist.List;

import java.io.Serializable;

/**
 * ClassName: linkedlist
 * Package: com.mysite
 * Description
 * 双向循环链表
 *
 * @Author zhl
 * @Create 2023/12/13 20:01
 * version 1.0
 */
public class CircleLinkedList<E> extends AbstractList<E> implements Serializable, List<E> {
    private Node<E> first;
    private Node<E> last;

    /**
     * 将第一个节点设为null
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 在指定的节点处添加元素（节点前）
     *
     * @param index   指定的索引
     * @param element 需要添加的元素
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }

    }

    /**
     * 添加前面的节点
     * @param element  需要添加的元素
     * @param succNode 在index处的节点
     */
    private void linkBefore(E element, Node<E> succNode) {
        //获取index处的前一个节点
        Node<E> predNode = succNode.prev;
        //添加的节点
        Node<E> newNode = new Node<>(predNode, element, succNode);
        //拼接
        succNode.prev = newNode;
        predNode.next = newNode;
        //如果succNode是first,则succNode.prev=last，predNode=last
        if (succNode == first) {     //index = 0 或 succNode == first 或 predNode == last
            first = newNode;
        }
        size++;
    }

    /**
     * 在末尾添加节点
     *
     * @param element 添加的元素
     */
    private void linkLast(E element) {
        Node<E> oldLast = last;
        Node<E> newNode = new Node<>(oldLast, element, first);
        last = newNode;
        //如果链表为空size = 0,oldLast可能为空
        if (oldLast == null) {   //第一次添加元素
            first = newNode;
            first.next = first;
            first.prev = first;
        } else {
            oldLast.next = newNode;
            first.prev = newNode;
        }
        size++;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (node.element == null) {
                    return index;
                }
                node = node.next;
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(node.element)) {
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
        //获取index处的节点
        Node<E> oldNode = node(index);
        E oldEle = oldNode.element;

        if (size == 1){
            first = null;
            last = null;
        }else {
            Node<E> predNode = oldNode.prev;
            Node<E> nextNode = oldNode.next;
            predNode.next = nextNode;
            nextNode.prev = predNode;

            if (index == 0) {  //index == 0     oldNode == first
                first = nextNode;   //头节点指向要删除的下一个节点
            }
            if (oldNode == last) {  //index == size - 1    oldNode == last
                last = predNode;    //尾节点指向要删除的上一个节点
            }
        }

        size--;

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
     *
     * @param index 需要查找索引的位置
     * @return 返回当前索引的节点
     */
    public Node<E> node(int index) {
        rangeCheck(index);
        //index小于size/2，节点在size的左边
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            //否则节点在size的右边
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node(i).element);
        }
        sb.append("]");

        return sb.toString();
    }
}
