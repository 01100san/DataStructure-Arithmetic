# DataStructure-Arithmetic
数据结构与算法的学习ing....、
## 线性表
### 动态数组(ArrayList)
[dsa-001-dynamicarray](./dsa-001-dynamicarray)
### 链表(LinkedList & SingleLinkedList)
[dsa-002-linkedlist](./dsa-002-LinkedList)
#### 双向链表 VS 动态数组
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
### 栈(Stack)
[dsa-003-Stack](./dsa-003-Stack)

### 队列(Queue)
[dsa-004-Queue](./dsa-004-Queue)
<hr>

## 树形结构
### BST、RBT、AVLT
[dsa-005-tree](./dsa-005-tree/src/main/java/com/mysite)

### TreeMap、TreeSet、ListSet
[dsa-006-SetAndMap](./dsa-006-SetAndMap/src/main/java/com/mysite)

### HashMap、LinkedHashMap、HashSet、LinkedHashSet
[dsa-007-Hash](./dsa-007-Hash/src/main/java/com/mysite)

### Heap(二叉堆)
[dsa-008-Heap](./dsa-008-Heap/src/main/java/com/mysite)

### PriorityQueue(优先级队列)
[dsa-009-PriorityQueue](./dsa-009-PriorityQueue/src/main/java/com/mysite)

### Trie(前缀树)
[dsa-010-Trie](./dsa-010-Trie/src/main/java/com/mysite)
