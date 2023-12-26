package com.mysite.avltree;

import java.util.Comparator;

/**
 * ClassName: BinarySearchTree
 * Package: com.mysite
 * Description
 *  二叉搜索树(Binary Search Tree)
 * @Author zhl
 * @Create 2023/12/21 20:08
 * version 1.0
 */
public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator;
    public BST() {
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    public void add(E element) {
        elementNotNullCheck(element);
        //添加的是第一个节点
        if (root == null){
            //root = new Node<>(element,null);
            root = createNode(element,null);
            size ++;
            //新添加节点后的处理
            afterAdd(root);
            return;
        }
        //添加的不是第一个节点
        //找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;    //记录节点插入的方向
        while (node != null){
            cmp = compare(element, node.element);
            parent = node;  //暂存node.left 和 node.right的父节点
            if (cmp > 0){
                node = node.right;
            }else if (cmp < 0){
                node = node.left;
            }else {     //相等
                node.element = element; //覆盖原来的元素
                return;
            }
        }
        //创建新的节点，将元素值赋给newNode
        //Node<E> newNode = new Node<>(element,parent);
        Node<E> newNode = createNode(element,parent);
        //看看插入的节点在父节点的哪个位置
        if (cmp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        size ++;
        //新添加节点后的处理
        afterAdd(newNode);
    }

    /**
     * 留给AVL树处理添加的节点
     * @param node
     */
    protected void afterAdd(Node<E> node){}
    /**
     * 留给AVL树处理添加的节点
     * @param node
     */
    protected void afterRemove(Node<E> node){}
    public boolean contains(E element){
        return node(element) != null;
    }

    //删除叶子节点
    //删除度为1的节点
    //删除度为2的节点 =》前驱或后继（前驱或后继节点的度只可能是1或0）
    public void remove(E element){
        remove(node(element));
    }
    private void remove(Node<E> node){
        if (node == null)
            return;

        size --;
        if (node.hasTwoChildren()){     //度为2 的节点
            //找到前驱或后继节点
            Node<E> succNode = successor(node);
            //用后继节点的值覆盖掉度为2的节点的值
            node.element = succNode.element;
            //删除后继节点
            node = succNode;
        }

        //删除node节点（node的度必然是1或者是0）
        Node<E> repNode = (node.left != null) ? node.left : node.right;

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
            afterRemove(node);
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
    }
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null){
            int cmp = compare(element,node.element);
            if (cmp == 0)
                return node;
            if (cmp > 0) {
                node = node.right;
            }else {
                node = node.left;
            }
        }
        return null;
    }
    private void elementNotNullCheck(E element){
        if (element == null){
            throw new IllegalArgumentException("element is always null ");
        }
    }
    //前序遍历
    /*public void preorderTraversal(){
        preorderTraversal(root);    //传入根节点
    }*/
    /*private void preorderTraversal(Node<E> node){
        if (node == null) return;

        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }*/
    //中序遍历
    /*public void inorderTraversal(){
        inorderTraversal(root);
    }*/
    /*private void inorderTraversal(Node<E> node){
        if (node == null) return;

        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }*/
    //后序遍历
    /*public void postorderTraversal(){
        postorderTraversal(root);
    }*/
    /*private void postorderTraversal(Node<E> node){
        if (node == null) return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }*/
    //层序遍历
    /*public void levelOrderTraversal(){
        if (root == null) return;
        //创建队列，保存节点
        Queue<Node<E>> queue = new LinkedList<>();
        //先让root节点入队
        queue.offer(root);
        //循环，直到queue不为空
        while (! queue.isEmpty()){
            //取出队列中的节点
            Node<E> node = queue.poll();
            System.out.println(node.element);

            //如果取出的节点的队列左子节点不为空，将其左子节点添加到队列中
            if (node.left != null){
                queue.offer(node.left);
            }
            //如果取出的节点的队列右子节点不为空，将其右子节点添加到队列中
            if (node.right != null){
                queue.offer(node.right);
            }
        }
    }*/
    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root,sb,"");
        return sb.toString();
    }*/

    /**
     * 返回值=0，e1=e2；返回值大于0，e1 > e2；返回值小于0，e1 < e2
     */
    public int compare(E e1,E e2){
        //如果比较器不等于空，说明用户想使用比较器，就用用户定义的比较器进行比较
        if (comparator != null){
            return comparator.compare(e1,e2);
        }
        //否则让用户实现Comparable接口，实现compareTo方法，java中内置了一些Comparable接口，例如Integer,Double
        return ((Comparable<E>)e1).compareTo(e2);
    }
}
