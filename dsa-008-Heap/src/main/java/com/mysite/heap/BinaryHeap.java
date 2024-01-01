package com.mysite.heap;

import com.mysite.printer.BinaryTreeInfo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ClassName: BinaryHeap
 * Package: com.mysite.heap
 * Description
 *  二叉堆 -> 最大堆
 * @Author zhl
 * @Create 2024/1/1 14:15
 * version 1.0
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo{
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 16;
    public BinaryHeap(E[] elements, Comparator<E> comparator){
        super(comparator);
        if (elements == null || elements.length == 0){
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }else{
            size = elements.length;
            int capacity = elements.length < 10 ? DEFAULT_CAPACITY : elements.length;
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();  //建堆
        }
    }
    public BinaryHeap(E[] elements){
        this(elements,null);
    }
    public BinaryHeap(Comparator<E> comparator){
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }
    public BinaryHeap(){
        this(null,null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    //logn，上滤
    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);

        elements[size ++] = element;
        siftUp(size - 1);
    }
    //拿到堆顶元素
    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    //删除堆顶元素
    @Override
    public E remove() {
        emptyCheck();
        E root = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size --;
        //下滤
        siftDown(0);
        return root;
    }

    //替换后下滤
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0){
            elements[0] = element;
            size ++;
        }else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }
    /**批量建堆**/
    private void heapify(){
        //自上而下的上滤
        /*for (int i = 0; i < size; i++) {
            siftUp(i);
        }*/
        //自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }
    //让index位置的元素下滤
    private void siftDown(int index){
        E newRoot = elements[index];
        int half = size >> 1;
        while (index < half){   //index有子节点，进入循环，index小于第一个叶子节点的索引
            int leftIndex = (index << 1) + 1;
            E leftEle = elements[leftIndex];
            //如果有右子节点
            int rightIndex = leftIndex + 1;
            if (rightIndex < size && compare(elements[rightIndex],leftEle) > 0){
                leftEle = elements[rightIndex];
                leftIndex = rightIndex;
            }
            if (compare(newRoot,leftEle) >= 0) break;   //如果newRoot >= repEle，退出循环
            //将子节点放到index位置
            elements[index] = leftEle;
            index = leftIndex;
        }
        elements[index] = newRoot;
    }
    //让index位置的元素上滤
    private void siftUp(int index){
        /*E ele = elements[index];
        while (index > 0) {
            int preIndex = ((index - 1) >> 1);   //根据完全二叉树的性质
            E preEle = elements[preIndex];      //取出父节点
            if (compare(ele,preEle) <= 0)   return;

            //交换父子
            E tmp = elements[index];
            elements[index] = elements[preIndex];
            elements[preIndex] = tmp;

            index = preIndex;
        }*/
        E ele = elements[index];
        while (index > 0) {
            int preIndex = ((index - 1) >> 1);   //根据完全二叉树的性质
            E preEle = elements[preIndex];      //取出父节点
            if (compare(ele,preEle) <= 0)   break;
            //将父元素存储在index位置
            elements[index] = preEle;
            index = preIndex;
        }
        elements[index] = ele;
    }
    private void emptyCheck(){
        if (size == 0){
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }
    private void elementNotNullCheck(E element){
        if (element == null){
            throw new IndexOutOfBoundsException("Element must not null");
        }
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

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = (int) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = (int) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
