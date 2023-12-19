package com.mysite.queue;

import org.junit.Test;

/**
 * ClassName: TestQueue
 * Package: com.mysite.queue
 * Description
 *  测试队列 =》由双向链表构成的
 * @Author zhl
 * @Create 2023/12/19 19:05
 * version 1.0
 */
public class TestQueue {
    @Test
    public void test1(){
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(12);
        queue.enQueue(13);
        queue.enQueue(14);
        queue.enQueue(15);

        System.out.println(queue.front());
        /*queue.clear();*/
        System.out.println("*****************************");
        System.out.println("队列的长度为：" + queue.size());
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }

    }
}
