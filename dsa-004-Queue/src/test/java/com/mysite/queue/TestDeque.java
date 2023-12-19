package com.mysite.queue;

import org.junit.Test;

/**
 * ClassName: TestDeque
 * Package: com.mysite.deque
 * Description
 *  测试双端队列
 * @Author zhl
 * @Create 2023/12/19 20:17
 * version 1.0
 */
public class TestDeque {
    @Test
    public void test1(){
        Deque<Integer> deque = new Deque<>();
        //从头入队
        deque.enQueueFront(11);
        deque.enQueueFront(22);
        //从队尾入队
        deque.enQueueRear(33);
        deque.enQueueRear(44);

        System.out.println(deque.front());
        /*deque.clear();*/
        System.out.println("*****************************");
        System.out.println("队列的长度为：" + deque.size());
        while (!deque.isEmpty()){
            System.out.println(deque.deQueueFront());     //22 11 33 44
            //System.out.println(deque.deQueueRear());    //44 33 11 22
        }
    }
}
