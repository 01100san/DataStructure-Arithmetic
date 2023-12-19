package com.mysite.queue;

import com.mysite.circle.CircleQueue;
import org.junit.Test;

/**
 * ClassName: TestCircleQueue
 * Package: com.mysite.queue
 * Description
 * 测试循环队列
 * @Author zhl
 * @Create 2023/12/19 21:06
 * version 1.0
 */
public class TestCircleQueue {
    @Test
    public void test1(){
        CircleQueue<Integer> queue = new CircleQueue<>();
        //0 1 2 3 4 5 6 7 8 9
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        //null null null null null 5 6 7 8 9
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        //15, 16, 17, 18, 19, 5, 6, 7, 8, 9
        for (int i = 15; i < 20; i++) {
            queue.enQueue(i);
        }

        //方式一：15 16 17 18 19 99 88 null null null 5 6 7 8 9
        //方式二：5, 6, 7, 8, 9, 15, 16, 17, 18, 19, 99, 88, null, null, null
        queue.enQueue(99);
        queue.enQueue(88);
        System.out.println("队列头：" + queue.front());

        System.out.println("循环队列的size = " + queue.size());
        System.out.println(queue);
        System.out.println();
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}






