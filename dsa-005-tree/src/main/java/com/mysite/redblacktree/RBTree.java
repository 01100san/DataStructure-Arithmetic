package com.mysite.redblacktree;

import com.mysite.avltree.BBST;
import com.mysite.avltree.BST;

import java.util.Comparator;

/**
 * ClassName: RBTree
 * Package: com.mysite.redblacktree
 * Description
 *
 * @Author zhl
 * @Create 2023/12/27 16:36
 * version 1.0
 */
public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree(){
        this(null);
    }
    public RBTree(Comparator<E> comparator){
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //添加的是根节点
        if (parent == null){
            black(node);
            return;
        }
        //如果父节点是黑色，直接返回
        if(isBlack(parent)) return;

        //叔父节点
        Node<E> uncle = parent.sibling();
        //祖父节点
        Node<E> grand = parent.parent;
        //叔父节点是红色，染色
        if (isRed(uncle)){
            black(parent);
            black(uncle);
            //把祖父节点当作新添加的节点
            afterAdd(red(grand));
            return;
        }
        if (parent.isLeftChild()){      //L
            if (node.isLeftChild()){    //LL
                black(parent);
                red(grand);
                rotateRight(grand);
            }else {             //LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {                         //R
            if (node.isLeftChild()){    //RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }else {             //RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    private Node<E> color(Node<E> node, boolean color){
        if (node == null)
            return null;
        ((RBNode<E>)node).color = color;
        return node;
    }
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    protected static class RBNode<E> extends Node<E>{
        boolean color;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED){
                str += "R_";
            }
            return str;
        }
    }
}
