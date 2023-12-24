package com.mysite.avltree;


import java.util.Comparator;
import java.util.Map;

/**
 * ClassName: AVLTree
 * Package: com.mysite.avltree
 * Description
 *
 * @Author zhl
 * @Create 2023/12/24 21:03
 * version 1.0
 */
public class AVLTree<E> extends BST<E> {
    public AVLTree(){}
    public AVLTree(Comparator<E> comparator){
        super(comparator);
    }
    @Override
    protected void afterAdd(Node<E> node) {
        while ( (node = node.parent) != null){

        }
    }
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }

    public boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E>{
        int height;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public int balanceFactor(){
            int leftHeight = (left == null) ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = (right == null) ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }
    }
}
