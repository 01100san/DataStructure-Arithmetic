package com.mysite;

import com.mysite.linkedlist.ArrayList;
import com.mysite.linkedlist.LinkedList;
import com.mysite.linkedlist.List;
import org.junit.jupiter.api.Test;

/**
 * ClassName: TestLinkedList
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/13 20:02
 * version 1.0
 */
public class TestLinkedList {
    @Test
    public void test1(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.add(null);
        list.add(12);

        list.add(list.size(),99);

        //测试remove(E element)
        /*boolean remove = list.remove(null);
        System.out.println(remove);*/

        //测试add(int index,E element)
        /*list.add(5,20);*/

        /*int count = list.indexOf(null);
        System.out.println(count);*/

        //测试remove(int index)
        /*Integer oldEle = list.remove(0);
        System.out.println(oldEle);*/

        //测试indexOf(int index)
        /*int index = list.indexOf(2);
        System.out.println("当前位置的索引：" + index);*/

        //测试get(E element)
        /*Integer integer = list.get(3);
        System.out.println(integer);*/

        //测试size()
        System.out.println("节点的数量：" + list.size());
        System.out.println(list);
    }

    @Test
    public void test2(){
        java.util.List<Integer> list = new java.util.LinkedList<Integer>();
        list.add(21);
        int count = list.indexOf(1);
        System.out.println(count);
    }

    /**
     * 测试缩容和扩容
     */
    @Test
    public void test3(){
        List<Integer> list = new ArrayList<>();
        /*java.util.List<Integer> list = new ArrayList<>();*/
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        for (int i = 0; i < 50; i++) {
            list.remove(0);
        }

        System.out.println(list);

    }
}
