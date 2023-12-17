# DataStructure-Arithmetic
数据结构与算法的学习ing....、
## 动态数组
[dsa-001-dynamicarray](../DataStructure-Arithmetic/dsa-001-dynamicarray)
## 链表
[dsa-002-linkedlist](../DataStructure-Arithmetic/dsa-002-LinkedList)
### 双向链表 VS 动态数组
<ul>
    <li>动态数组：
        开辟、销毁内存空间的次数相对较少，但可能造成内存空间浪费（可通过缩容解决）
    </li>
    <li>双向链表：
        开辟、销毁内存空间的次数相对较多，但不会造成内存空间的浪费
    </li>
</ul>

如果频繁在尾部进行添加、删除操作,动态数组、双向链表均可选择<p>
如果频繁在头部进行添加、删除操作,建议选择使用双向链表<p>
如果有频繁的(在任意位置)添加、删除操作,建议选择使用双向链表<p>
如果有频繁的查询操作（随机访问操作）,建议选择使用动态数组<p>








