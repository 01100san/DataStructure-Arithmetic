package com.mysite.map;

import com.mysite.printer.BinaryTreeInfo;
import com.mysite.printer.BinaryTrees;
import sun.util.locale.provider.DateFormatProviderImpl;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.security.cert.PolicyQualifierInfo;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * ClassName: Hash
 * Package: com.mysite.map
 * Description
 *  数组+红黑树
 *  table[] 直接存储红黑树节点
 * @Author zhl
 * @Create 2023/12/30 14:12
 * version 1.0
 */
public class HashMap<K, V> implements Map<K, V>{
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4;   //默认的数组容量为16
    private int size = 0;   //所有红黑树节点的总数量
    private Node<K, V>[] table;

    public HashMap(){
        table = new Node[DEFAULT_CAPACITY];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        int index = index(key);
        //取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null){
            root = new Node<>(key,value,null);
            table[index] = root;
            size ++;
            afterPut(root);
            return null;
        }
        //添加新的节点到红黑树
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;    //记录节点插入的方向
        K k1 = key;
        int h1 = key == null ? 0 : key.hashCode();
        Node<K, V> result = null;
        boolean searched = false;
        do {
            parent = node;  //暂存node.left 和 node.right的父节点
            K k2 = node.key;
            int h2 = node.hash;

            if (h1 > h2){
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1,k2)){
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable) {
                cmp = ((Comparable) k1).compareTo(k2);
            }else if (!searched){ //先扫描，再根据内存地址大小决定左右
                if ((node.left != null && (result = node(node.left,k1)) != null)
                        || (node.right != null && (result = node(node.right,k1)) != null)){
                    //已经存在这个Key
                    node = result;
                    cmp = 0;
                }else { //不存在这个key
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }else {     //searched = true
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

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
            
        }while (node != null);
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

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0)  return false;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null)   continue;

            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K, V> node = queue.poll();
                if (Objects.equals(node.value,value))  return true;

                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null)  return;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null)   continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key,node.value)) return;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
    }

    /**
     * 根据key生成对应的索引值
     * @param key
     * @return
     */
    private int index(K key){
        if (key == null) return 0;
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & (table.length - 1);
    }
    /**直接拿到node中的hash，不需要再算一遍**/
    private int index(Node<K, V> node){
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }
    private Node<K, V> node(K key){
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root,key);
    }
    private Node<K, V> node(Node<K, V> node,K k1){
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K, V> result = null;
        do {
            K k2 = node.key;
            int h2 = node.hash;
            //先比较哈希值cmp=h1-h2 =》存在问题：如果h1和h2都很大，并且h1>0,h2<0,运算后cmp可能溢出(cmp<0)导致h1<h2，但事实是h1>h2
            if (h1 > h2){
                node = node.right;
            }else if (h1 < h2){
                node = node.left;
            } else if (Objects.equals(k1,k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable){
                int cmp = ((Comparable) k1).compareTo(k2);
                if (cmp > 0){
                    node = node.right;
                }else if (cmp < 0){
                    node = node.left;
                }else {
                    return node;
                }
            }else if (node.right != null && (result = node(node.right,k1)) != null){
                return result;
            }else if (node.left != null && (result = node(node.left,k1)) != null){
                return result;
            }else {
                return null;
            }
        }while (node != null);
        return null;
    }

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
        int index = index(node);
        //node是度为1 的节点
        if (repNode != null){
            repNode.parent = node.parent;
            //node是度为1 的节点，并且是根节点
            if (node.parent == null)
                table[index] = repNode;
            else if (node.parent.left == node){
                node.parent.left = repNode;
            }else{
                node.parent.right = repNode;
            }

            //删除节点之后的处理-->AVL
            afterRemove(repNode);
        }else if (node.parent == null){
            //node是度为0 的节点，并且是根节点
            table[index] = null;

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
    private void afterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
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
    private void afterRotate (Node<K, V> parent,Node<K, V> grand,Node<K, V> child){
        //让parent成为子树的根节点
        parent.parent = grand.parent;
        //更新grand.parent中的左或右节点
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        }else if (grand.isRightChild()){
            grand.parent.right = parent;
        }else { //grand == root
            table[index(grand)] = parent;
        }

        //更新child中的parent
        if (child != null){
            child.parent = grand;
        }
        //更新grand的parent
        grand.parent = parent;
    }
    private static class Node<K, V>{
        int hash;
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;   //左节点
        Node<K, V> right;  //右节点
        Node<K, V> parent; //父节点
        public Node(K key,V value,Node<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.hash = key == null ? 0 : key.hashCode();
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
        public Node<K, V> sibling() {
            if (isLeftChild()){
                return parent.right;
            }
            if (isRightChild()){
                return parent.left;
            }
            return null;
        }
        public String toString(){
            //return "K(" + key + ")_V(" + value + ")";
            return "V(" + value + ")";
        }
    }

    /**
     *  通过hash比较key的大小
     * @param k1
     * @param k2
     * @param h1  k1的hashCode
     * @param h2  k2的hashCode
     * @return
     */
    private int compare(K k1,K k2, int h1, int h2){
        //1.比较哈希值
        int result =  h1 - h2;
        if (result != 0)    return result;
        //2.比较equals
        if (Objects.equals(k1,k2)) return 0;   //同一个key覆盖
        //哈希值相等，但是k1,k2不是同一个key
        //比较类名
        //4.1同一种类型,且具有可比较性
        if (k1 != null && k2 != null
                && k1.getClass() == k2.getClass()
                && k1 instanceof Comparable){
            if (k1 instanceof Comparable){
                return ((Comparable) k1).compareTo(k2);
            }
        }

        //4.2同一种类型，哈希值相等，但是不具有可比较性
        //5.k1不为null,k2为null
        //6.k1为null,k2不为null
        return System.identityHashCode(k1) - System.identityHashCode(k2);  //比较内存地址
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
    public void print(){
        if (size == 0)  return;
        for (int i = 0; i < table.length; i++) {
            final Node<K, V> node = table[i];
            System.out.println("【index = " + i + " 】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return node;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>) node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>) node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("------------------------");
        }
    }
}
