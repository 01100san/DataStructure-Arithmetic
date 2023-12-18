# 线性表
## [队列(Queue)](./main/java/com/mysite/queue/Queue.java)
队列是一种特殊的线性表，只能在头尾两端进行操作<p>
<ul>
    <li>队尾（rear）:只能从队尾添加元素，一般叫做enQueue，入队</li>
    <li>对头（front）:只能从对头移除元素，一般叫做deQueue，出队</li>
    <li>先进先出的的原则，First In First，FIF</li>
</ul>

### 栈的接口设计
<ul>
    <li>int size(); 获取元素的数量</li>
    <li>boolean isEmpty(); 是否为空</li>
    <li>void enQueue(E element); 入队</li>
    <li>E deQueue(); 出队</li>
    <li>E front(); 获取队列的头元素</li>
    <li>void clear();</li>
</ul>


