package com.mysite;

import com.mysite.linkedlist.LinkedList;
import com.mysite.linkedlist.List;
import org.junit.jupiter.api.Test;

/**
 * ClassName: TestLinkedList
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/15 22:19
 * version 1.0
 */
public class TestLinkedList {
    @Test
    public void test1(){
        List<Integer> list = new LinkedList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(null);
        list.add(14);
        list.add(15);

        //测试add(int index,E element)
        //list.add(3,99);
        //测试add插入头节点
        //list.add(0,99);
        //测试add插入尾节点
        //list.add(list.size(),99);

        //测试remove(E element)
        /*boolean remove = list.remove(null);
        System.out.println(remove);*/

        //测试remove(int index)
        /*Integer oldEle = list.remove(0);
        System.out.println(oldEle);*/

        /*int count = list.indexOf(null);
        System.out.println(count);*/


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
}
