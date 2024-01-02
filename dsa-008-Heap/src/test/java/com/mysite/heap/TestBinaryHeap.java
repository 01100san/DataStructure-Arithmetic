package com.mysite.heap;

import com.mysite.printer.BinaryTreeInfo;
import com.mysite.printer.BinaryTrees;
import org.junit.Test;

import java.util.Comparator;

/**
 * ClassName: TestBinaryHeap
 * Package: com.mysite.heap
 * Description
 *
 * @Author zhl
 * @Create 2024/1/1 14:56
 * version 1.0
 */
public class TestBinaryHeap {
    @Test
    public void test1(){
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
        System.out.println();
        /*System.out.println(heap.remove());
        BinaryTrees.println(heap);*/
        System.out.println(heap.replace(70));
        BinaryTrees.println(heap);
    }
    @Test
    public void test2(){
        int[] data = new int[]{36, 44, 93, 82, 27, 60, 55, 38};
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for (int i = 0; i < data.length; i++) {
            heap.add(data[i]);
        }
        BinaryTrees.println(heap);
    }
    @Test
    public void test3(){
        Integer[] data = new Integer[]{36, 10, 40, 93, 82, 27, 60, 55, 38};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //return o1 - o2;   //大顶堆
                return o2 - o1;     //小顶堆
            }
        });

        BinaryTrees.println(heap);
    }
    @Test
    public void test4(){
        Integer[] data = new Integer[]{43, 89, 32, 82, 72, 28, 38, 62, 7, 56, 1, 76, 78, 27, 93, 91};
        //新建一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;     //小顶堆
            }
        });
        //****************找出前k个最大的元素***********
        int k = 4;
        for (int i = 0; i < data.length; i++) {
            if (heap.size < k){ //前k个数添加到小顶堆
                heap.add(data[i]);
            }else {             //第k+1个数
                if (data[i] > heap.get()){
                    heap.replace(data[i]);
                }
            }
        }
        BinaryTrees.println(heap);
    }
}
