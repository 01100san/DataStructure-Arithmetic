package com.mysite.queue;

import com.mysite.Person;
import org.junit.Test;

/**
 * ClassName: TestPriorityQueue
 * Package: com.mysite.queue
 * Description
 *
 * @Author zhl
 * @Create 2024/1/1 21:50
 * version 1.0
 */
public class TestPriorityQueue {
    @Test
    public void test1(){
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.enQueue(new Person("a",1));
        queue.enQueue(new Person("b",10));
        queue.enQueue(new Person("c",3));
        queue.enQueue(new Person("d",5));
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}
