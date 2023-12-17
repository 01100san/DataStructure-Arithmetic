package com.mysite;

import com.mysite.linkedlist.List;
import com.mysite.linkedlist.circle.CircleLinkedList;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.junit.jupiter.api.Test;

/**
 * ClassName: TestCircleLinkedList
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/17 21:12
 * version 1.0
 */
public class TestCircleLinkedList {
    @Test
    public void test1(){
        List<Integer> list = new CircleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.add(null);
        list.add(12);

        //list.add(list.size(),99);
        //list.add(3,99);
        //list.remove(null);

        //Integer remove = list.remove(list.size() - 1);
        //Integer remove = list.remove(0);
        //System.out.println(remove);

        //测试size()
        System.out.println("节点的数量：" + list.size());
        System.out.println(list);
    }

    @Test
    public void testJosephus(){

        com.mysite.linkedlist.excise.CircleLinkedList<Integer> list = new com.mysite.linkedlist.excise.CircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }

        //指向头节点
        list.reset();

        //游戏规则：转三次删除
        while (!list.isEmpty()){
            list.next();
            list.next();
            System.out.println(list.remove());
        }

    }
}
