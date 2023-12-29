package com.mysite.avltree;

import java.util.Comparator;

/**
 * ClassName: BBST
 * Package: com.mysite.avltree
 * Description
 *  平衡二叉搜索树
 * @Author zhl
 * @Create 2023/12/27 21:22
 * version 1.0
 */
public class BBST<E> extends BST<E>{
    public BBST(){
    }
    public BBST(Comparator<E> comparator){
        super(comparator);
    }
    //左旋转
    protected void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        //旋转后的操作
        afterRotate(parent,grand,child);
    }
    //右旋转
    protected void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        //旋转后的操作
        afterRotate(parent,grand,child);
    }

    protected void afterRotate (Node<E> parent,Node<E> grand,Node<E> child){
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
}
