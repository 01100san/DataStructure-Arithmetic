# 线性表
## [队列(Queue)](./main/java/com/mysite/queue/Queue.java)
队列是一种特殊的线性表，只能在头尾两端进行操作<p>
<ul>
    <li>队尾（rear）:只能从队尾添加元素，一般叫做enQueue，入队</li>
    <li>对头（front）:只能从对头移除元素，一般叫做deQueue，出队</li>
    <li>先进先出的的原则，First In First，FIF</li>
</ul>

### 队列的接口设计
<ul>
    <li>int size(); 获取元素的数量</li>
    <li>boolean isEmpty(); 是否为空</li>
    <li>void enQueue(E element); 入队</li>
    <li>E deQueue(); 出队</li>
    <li>E front(); 获取队列的头元素</li>
    <li>void clear();</li>
</ul>

队列的内部实现可以利用以前学过的数据结构 =》 动态数组 / 双向链表<p>
优先使用双向链表，因为队列主要是往头尾操作元素<p>
实际上java.util.LinkedList<E>就实现了Queue<E>
## [双端队列(Deque)](./main/java/com/mysite/queue/Deque.java)
> 双端队列是能在头尾两端添加、删除的队列。
英文deque是double ended queue的简称
### 双端队列的接口设计
<ul>
    <li>int size(); 元素的数量</li>
    <li>boolean isEmpty(); 是否为空</li>
    <li>void enQueueRear(E element); 从队尾入队</li>
    <li>E deQueueFront(); 从队头出队</li>
    <li>void enQueueFront(E element); 从队头入队</li>
    <li>E deQueueRear(); 从队尾出队</li>
    <li>E front(); 获取队列的头元素</li>
    <li>E rear(); 获取队列的尾元素</li>
</ul>

## [循环队列(Circle Queue)](./main/java/com/mysite/circle/CircleQueue.java)
> 队列底层也可以使用动态数组实现，并且各项接口也可以优化到O(1)的时间复杂度
> 这个用数组实现并且优化之后的队列叫做：循环队列

这里的循环队列底层用数组实现<p>
接口设计和普通队列(Queue)的设计类似，结合了ArrayList< E>的用法

## [循环双端队列(Circle Deque)](./main/java/com/mysite/circle/CircleDeque.java)
循环双端队列：可以进行两端添加、删除操作的循环队列<p>
接口设计和双端队列的设计一样

>**对于循环队列和循环双端队列中取模运算的问题的优化`int index(int index)`：**
在计算机中取模、乘除运算效率比较低，经过分析[testMod](../src/test/java/com/mysite/queue/TestMod.java)
，并且(front + index)不可能超过 element.length的两倍

