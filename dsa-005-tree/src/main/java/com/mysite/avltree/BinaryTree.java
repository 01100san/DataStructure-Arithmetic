package com.mysite.avltree;

import com.mysite.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName: BinaryTree
 * Package: com.mysite.binarytree
 * Description
 *  二叉树
 * @Author zhl
 * @Create 2023/12/24 20:09
 * version 1.0
 */
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    //保留根节点
    protected Node<E> root;
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

    /**
     * 中序遍历
     * @param visitor  类似于用户自定义比较规则,得到元素element后，自定义操作
     */
    public void inOrder(Visitor<E> visitor){
        inOrder(root,visitor);
    }

    /**
     * 后序遍历
     * @param visitor  类似于用户自定义比较规则,得到元素element后，自定义操作
     */
    public void postOrder(Visitor<E> visitor){
        postOrder(root,visitor);
    }

    private void preOrder(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;

        visitor.visit(node.element);
        preOrder(node.left,visitor);
        preOrder(node.right,visitor);
    }

    protected void inOrder(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;

        inOrder(node.left,visitor);
        visitor.visit(node.element);
        inOrder(node.right,visitor);
    }

    private void postOrder(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor == null) return;

        postOrder(node.left,visitor);
        postOrder(node.right,visitor);
        visitor.visit(node.element);
    }

    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element,parent);
    }

    /**
     * 查找前驱节点
     * @param node
     * @return
     */
     protected Node<E> predecessor(Node<E> node){
        if (node == null) return null;

        //前驱节点在左子树中
        Node<E> preNode = node.left;
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
    protected Node<E> successor(Node<E> node){
        if (node == null) return null;

        if (node.right != null){
            Node<E> succNode = node.right;
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

    /**
     * 判断是否是完全二叉树
     * @return
     */
    public boolean isComplete(){
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();

            if (leaf && !node.isLeaf())
                return false;

            if (node.left != null){
                queue.offer(node.left);
            }else if (node.right != null){
                //node.left == null && node.right != null
                return false;
            }

            if (node.right != null){
                queue.offer(node.right);
            }else {
                //node.right == null && node.left == null
                //node.right == null && node.left != null
                leaf = true;
            }

        }
        return true;
    }

    /**
     * 通过迭代计算树的高度 => 使用层序遍历的方式
     * @return
     */
    public int heightByIter(){
        if (root == null) return 0;

        //树的高度
        int height = 0;
        //树的当前行元素长度
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelSize --;   //取出一个元素levelSize --

            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
            if (levelSize == 0){    //意味着即将要访问下一层
                levelSize = queue.size();
                height ++;
            }
        }
        return height;
    }

    /**
     * 通过递归计算树的高度
     * @return
     */
    public int heightByRecu(){
        return height(root);
    }
    //1.递归计算树的高度
    //树的高度实际上是子节点高度 + 1
    private int height(Node<E> node){
        if (node == null) return 0;

        return 1 + Math.max(height(node.left),height(node.right));
    }

    protected static class Node<E>{
        E element;
        Node<E> left;   //左节点
        Node<E> right;  //右节点
        Node<E> parent; //父节点
        public Node(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }
        public boolean isLeaf(){
            return left == null && right == null;
        }
        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
    }

    /**
     * 定义Visitor接口，让用户自己实现visit方法，获取遍历得到的元素值
     */
    public static interface Visitor<E>{
        public void visit(E element);
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
    public void toString(Node<E> node,StringBuilder sb,String prefix){
        if (node == null) return;

        sb.append(prefix).append(node.element).append("\n");
        toString(node.left,sb,prefix + "L---");
        toString(node.right,sb,prefix + "R---");
    }
}

