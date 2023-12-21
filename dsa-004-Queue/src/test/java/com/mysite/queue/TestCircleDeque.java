package com.mysite.queue;

import com.mysite.circle.CircleDeque;
import org.junit.Test;

/**
 * ClassName: TestCircleDeque
 * Package: com.mysite.deque
 * Description
 *  测试循环双端队列
 * @Author zhl
 * @Create 2023/12/20 19:40
 * version 1.0
 */
public class TestCircleDeque {
    @Test
    public void test1(){
        CircleDeque<Integer> deque = new CircleDeque<>();

        //头 5 4 3 2 1  100 101 102 103 104 尾
        //头 5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾
        //头 8 7 6 5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
        //头 10 9 8 7 6 5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null null null null 尾
        //真 [8, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, null, null, 10, 9]
        for (int i = 0; i < 10; i++) {
            deque.enQueueFront(i + 1);
            deque.enQueueRear(i + 100);
        }

        //头 [null, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, null, null, null, null, null, null, null]尾
        for (int i = 0; i < 3; i++) {
            deque.deQueueFront();
            deque.deQueueRear();
        }

        //头 [11, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, null, null, null, null, null, null, 12] 尾
        deque.enQueueFront(11);
        deque.enQueueFront(12);

        System.out.println("循环队列的size = " + deque.size());
        System.out.println(deque);
        System.out.println();
        while (!deque.isEmpty()){
            System.out.println(deque.deQueueFront());
        }
    }
}