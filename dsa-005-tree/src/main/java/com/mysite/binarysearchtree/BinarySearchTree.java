package com.mysite.binarysearchtree;

import com.mysite.printer.BinaryTreeInfo;
import com.sun.org.apache.xpath.internal.operations.Neg;
import org.omg.CORBA.IRObject;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName: BinarySearchTree
 * Package: com.mysite
 * Description
 *  二叉搜索树
 * @Author zhl
 * @Create 2023/12/21 20:08
 * version 1.0
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    //保留根节点
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void add(E element) {
        elementNotNullCheck(element);
        //添加的是第一个节点
        if (root == null){
            root = new Node<>(element,null);
            size ++;
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
        Node<E> newNode = new Node<>(element,parent);
        //看看插入的节点在父节点的哪个位置
        if (cmp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        size ++;
    }
    public boolean contains(E element){
        return false;
    }
    public void remove(E element){

    }
    public void clear(){
    }
    private void elementNotNullCheck(E element){
        if (element == null){
            throw new IllegalArgumentException("element: " + element + " is null ");
        }
    }

    //前序遍历
    public void preorderTraversal(){
        preorderTraversal(root);    //传入根节点
    }
    private void preorderTraversal(Node<E> node){
        if (node == null) return;

        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
    //中序遍历
    public void inorderTraversal(){
        inorderTraversal(root);
    }
    private void inorderTraversal(Node<E> node){
        if (node == null) return;

        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    //后序遍历
    public void postorderTraversal(){
        postorderTraversal(root);
    }
    private void postorderTraversal(Node<E> node){
        if (node == null) return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    //层序遍历
    public void levelOrderTraversal(){
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
    }

    /**
     * 层序遍历
     * @param visitor  类似于用户自定义比较规则,得到元素element后，自定义操作
     */
    public void levelOrder(Visitor<E> visitor){
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (! queue.isEmpty()){
            Node<E> node = queue.poll();
            visitor.visit(node.element);        //让用户得到遍历的node.element元素，自己操作

            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
    }
    /**
     * 前序遍历
     * @param visitor  类似于用户自定义比较规则,得到元素element后，自定义操作
     */
    public void preOrder(Visitor<E> visitor){
        preOrder(root,visitor);
    }
    private void preOrder(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;

        visitor.visit(node.element);
        preOrder(node.left,visitor);
        preOrder(node.right,visitor);
    }

    /**
     * 中序遍历
     * @param visitor  类似于用户自定义比较规则,得到元素element后，自定义操作
     */
    public void inOrder(Visitor<E> visitor){
        inOrder(root,visitor);
    }
    private void inOrder(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;

        inOrder(node.left,visitor);
        visitor.visit(node.element);
        inOrder(node.right,visitor);
    }

    /**
     * 后序遍历
     * @param visitor  类似于用户自定义比较规则,得到元素element后，自定义操作
     */
    public void postOrder(Visitor<E> visitor){
        postOrder(root,visitor);
    }
    private void postOrder(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;

        postOrder(node.left,visitor);
        postOrder(node.right,visitor);
        visitor.visit(node.element);
    }

    /**
     * @return  返回值=0，e1=e2；返回值大于0，e1 > e2；返回值小于0，e1 < e2
     */
    public int compare(E e1,E e2){
        //如果比较器不等于空，说明用户想使用比较器，就用用户定义的比较器进行比较
        if (comparator != null){
            return comparator.compare(e1,e2);
        }
        //否则让用户实现Comparable接口，实现compareTo方法，java中内置了一些Comparable接口，例如Integer,Double
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /**
     * 定义Visitor接口，让用户自己实现visit方法，获取遍历得到的元素值
     * @param <E>
     */
    public static interface Visitor<E>{
        public void visit(E element);
    }

    private static class Node<E>{
        E element;
        Node<E> left;   //左节点
        Node<E> right;  //右节点
        Node<E> parent; //父节点

        public Node(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }
    }
    @Override
    //返回根节点
    public Object root(){
        return root;
    }
    @Override
    //返回node的左节点
    public Object left(Object node){
        return ((Node<E>)node).left;
    }
    @Override
    //返回node的右节点
    public Object right(Object node){
        return ((Node<E>)node).right;
    }
    @Override
    //返回node的元素值
    public Object string(Object node){
        return ((Node<E>)node).element;
    }
}
