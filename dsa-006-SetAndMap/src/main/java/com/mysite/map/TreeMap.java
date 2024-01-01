package com.mysite.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName: TreeMap
 * Package: com.mysite.map
 * Description
 *  TreeMap 红黑树实现，logn
 * @Author zhl
 * @Create 2023/12/28 20:38
 * version 1.0
 */
public class TreeMap<K,V> implements Map<K,V>{
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    //保留根节点
    protected Node<K, V> root;
    private Comparator<K> comparator;
    public TreeMap() {
        this(null);
    }
    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void clear(){
        root = null;
        size = 0;
    }
    @Override
    public V put(K key, V value) {  //返回覆盖前的值
        keyNotNullCheck(key);

        //添加的是第一个节点
        if (root == null){
            //root = new Node<>(element,null);
            root = new Node<>(key,value,null);
            size ++;
            //新添加节点后的处理
            afterPut(root);
            return null;
        }
        //添加的不是第一个节点
        //找到父节点
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;    //记录节点插入的方向
        while (node != null) {
            cmp = compare(key, node.key);
            parent = node;  //暂存node.left 和 node.right的父节点
            if (cmp > 0){
                node = node.right;
            }else if (cmp < 0){
                node = node.left;
            }else {     //相等
                node.key = key;     //覆盖原来的元素
                V oldVal = node.value;
                node.value = value; //覆盖原来的元素
                return oldVal;
            }
        }
        //创建新的节点，将元素值赋给newNode
        //Node<K, V> newNode = new Node<>(element,parent);
        Node<K, V> newNode = new Node<>(key,value,parent);
        //看看插入的节点在父节点的哪个位置
        if (cmp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        size ++;
        //新添加节点后的处理
        afterPut(newNode);
        return null;
    }
    private void afterPut(Node<K,V> node){
        Node<K, V> parent = node.parent;
        //添加的是根节点
        if (parent == null){
            black(node);
            return;
        }
        //如果父节点是黑色，直接返回
        if(isBlack(parent)) return;

        //叔父节点
        Node<K, V> uncle = parent.sibling();
        //祖父节点
        Node<K, V> grand = red(parent.parent);
        //叔父节点是红色，染色
        if (isRed(uncle)){
            black(parent);
            black(uncle);
            //把祖父节点当作新添加的节点
            afterPut(grand);
            return;
        }
        if (parent.isLeftChild()){      //L
            if (node.isLeftChild()){    //LL
                black(parent);
            }else {             //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else {                         //R
            if (node.isLeftChild()){    //RL
                black(node);
                rotateRight(parent);
            }else {             //RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }
    //左旋转
    private void rotateLeft(Node<K, V> grand){
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;

        //旋转后的操作
        afterRotate(grand,parent,child);
    }
    //右旋转
    private void rotateRight(Node<K, V> grand){
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;

        //旋转后的操作
        afterRotate(grand,parent,child);
    }
    private void afterRotate (Node<K, V> grand,Node<K, V> parent,Node<K, V> child){
        //让parent成为子树的根节点
        parent.parent = grand.parent;
        //更新grand.parent中的左或右节点
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        }else if (grand.isRightChild()){
            grand.parent.right = parent;
        }else { //grand == root
            root = parent;
        }

        //更新child中的parent
        if (child != null){
            child.parent = grand;
        }
        //更新grand的parent
        grand.parent = parent;
    }
    private void keyNotNullCheck(K key){
        if (key == null){
            throw new IllegalArgumentException("key is always null ");
        }
    }
    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return (node != null) ? node.value : null;
    }
    @Override
    public V remove(K key) {
        return remove(node(key));
    }
    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }
    private boolean valEquals(V v1, V v2){
        return v1 == null ? v2 == null : v1.equals(v2);
    }
    @Override
    public boolean containsValue(V value) { //层序遍历
        if (root == null) return false;

        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.offer(root);

        while (! queue.isEmpty()){
            Node<K, V> node = queue.poll();
            if (valEquals(value,node.value)) return true;

            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }
    @Override
    public void traversal(Visitor<K, V> visitor) {  //中序遍历
        if (visitor == null) return;
        traversal(root,visitor);
    }
    private void traversal(Node<K, V> node,Visitor<K, V> visitor){
        if (node == null || visitor.stop) return;

        traversal(node.left,visitor);

        if (visitor.stop) return;
        visitor.visit(node.key,node.value);

        traversal(node.right,visitor);
    }
    private V remove(Node<K, V> node){
        if (node == null) return null;
        V oldVal = node.value;
        size --;

        if (node.hasTwoChildren()){     //度为2 的节点
            //找到前驱或后继节点
            Node<K, V> succNode = successor(node);
            //用后继节点的值覆盖掉度为2的节点的值
            node.key = succNode.key;
            node.value = succNode.value;
            //删除后继节点
            node = succNode;
        }

        //删除node节点（node的度必然是1或者是0）
        Node<K, V> repNode = (node.left != null) ? node.left : node.right;

        //node是度为1 的节点
        if (repNode != null){
            repNode.parent = node.parent;
            //node是度为1 的节点，并且是根节点
            if (node.parent == null)
                root = repNode;
            else if (node.parent.left == node){
                node.parent.left = repNode;
            }else{
                node.parent.right = repNode;
            }

            //删除节点之后的处理-->AVL
            afterRemove(repNode);
        }else if (node.parent == null){
            //node是度为0 的节点，并且是根节点
            root = null;

            //删除节点之后的处理-->AVL
            afterRemove(node);
        }else {
            //node是度为0 的节点，但不是根节点
            if (node == node.parent.left){
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }

            //删除节点之后的处理-->AVL
            afterRemove(node);
        }
        return oldVal;
    }
    //-----------------------------******未实现
    private void afterRemove(Node<K, V> node){}
    /**
     * 查找前驱节点
     * @param node
     * @return
     */
    private Node<K, V> predecessor(Node<K, V> node){
        if (node == null) return null;

        //前驱节点在左子树中
        Node<K, V> preNode = node.left;
        if (preNode != null){
            while (preNode.right != null){
                preNode = preNode.right;
            }
            return preNode;
        }
        //前驱节点是父节点
        while (node.parent != null && node == node.parent.left){
            node = node.parent;
        }

        //node.parent == null
        //node == node.parent.right
        return node.parent;
    }
    /**
     * 查找后继节点
     * @param node
     * @return
     */
    private Node<K, V> successor(Node<K, V> node){
        if (node == null) return null;

        Node<K, V> succNode = node.right;
        if (succNode != null){
            while (succNode.left != null){
                succNode = succNode.left;
            }
            return succNode;
        }

        while (node.parent != null && node == node.parent.right){
            node = node.parent;
        }

        //node.parent == null
        //node == node.parent.left
        return node.parent;
    }
    private static class Node<K, V>{
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;   //左节点
        Node<K, V> right;  //右节点
        Node<K, V> parent; //父节点
        public Node(K Key,V value,Node<K, V> parent){
            this.key = Key;
            this.value = value;
            this.parent = parent;
        }
        public boolean isLeaf(){
            return left == null && right == null;
        }
        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }
        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }
        //判断是否是兄弟节点
        public Node<K, V> sibling(){
            if (isLeftChild()){
                return parent.right;
            }
            if (isRightChild()){
                return parent.left;
            }
            return null;
        }
    }
    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while (node != null){
            int cmp = compare(key,node.key);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            }else {
                node = node.left;
            }
        }
        return null;
    }
    private int compare(K e1,K e2){
        //如果比较器不等于空，说明用户想使用比较器，就用用户定义的比较器进行比较
        if (comparator != null){
            return comparator.compare(e1,e2);
        }
        //否则让用户实现Comparable接口，实现compareTo方法，java中内置了一些Comparable接口，例如Integer,Double
        return ((Comparable<K>)e1).compareTo(e2);
    }
    private Node<K, V> color(Node<K, V> node, boolean color){
        if (node == null) return node;
        node.color = color;
        return node;
    }
    private Node<K, V> red(Node<K, V> node){
        return color(node,RED);
    }
    private Node<K, V> black(Node<K, V> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<K, V> node){
        return node == null ? BLACK : node.color;
    }
    private boolean isBlack(Node<K, V> node){
        return colorOf(node) == BLACK;
    }
    private boolean isRed(Node<K, V> node){
        return colorOf(node) == RED;
    }
}
