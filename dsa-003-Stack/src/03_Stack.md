# 线性表
## [栈(Stack)](./main/java/com/mysite/stack/Stack.java)
栈是一种特殊的线性表，只能在一端进行操作<p>
<ul>
    <li>往栈中添加元素的操作，一般叫做push，入栈</li>
    <li>往栈中移除元素的操作，一般叫做pop，出栈(只能移除栈顶元素，也叫做：弹出栈顶元素)</li>
    <li>后进先出的原则，List In First Out, LIFO</li>
</ul>

>注意：这里说的"栈"，与内存中的"栈空间"是两个不同的概念
### 栈的接口设计
<ul>
    <li>int size(); 获取元素的数量</li>
    <li>boolean isEmpty(); 是否为空</li>
    <li>void push(E element); 入栈</li>
    <li>E pop(); 出栈</li>
    <li>E top(); 获取栈顶元素</li>
</ul>

栈的内部实现可以利用以前学过的数据结构 =》 动态数组 / 双向链表<p>
优先使用动态数组<p>
栈的应用：浏览器的前进、后退，软件的撤销(Undo)、恢复(Redo)功能
底层是两个栈