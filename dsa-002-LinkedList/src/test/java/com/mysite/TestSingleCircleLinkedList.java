package com.mysite;

import com.mysite.linkedlist.List;
import com.mysite.linkedlist.circle.SingleCircleLinkedList;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.junit.Test;

/**
 * ClassName: TestSingleCircleLinkedList
 * Package: com.mysite
 * Description
 *  测试单向循环链表
 * @Author zhl
 * @Create 2023/12/17 0:14
 * version 1.0
 */
public class TestSingleCircleLinkedList {
    @Test
    public void test1(){
        List<Integer> list = new SingleCircleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.add(null);
        list.add(12);

        //list.add(list.size(),99);

        //测试remove(E element)
        /*boolean remove = list.remove(null);
        System.out.println(remove);*/
        //测试删除第一个节点
        /*Integer remove = list.remove(0);
        System.out.println(remove);*/

        //测试add(int index,E element)
        /*list.add(5,20);*/
        //测试在index = 0处，添加节点
        //list.add(0,88);

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
}



