package com.mysite.avltree;

import java.util.Comparator;

/**
 * ClassName: AVLTree
 * Package: com.mysite.avltree
 * Description
 *
 * @Author zhl
 * @Create 2023/12/24 21:03
 * version 1.0
 */
public class AVLTree<E> extends BBST<E> {
    public AVLTree(){}
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    @Override
    protected void afterAdd(Node<E> node) {
        while ( (node = node.parent) != null){
            if (isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢复平衡
                rebalance(node);
                //恢复平衡后
                break;
            }
        }
    }

    /**
     * 恢复平衡
     * @param grand  高度最低的不平衡节点
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode) grand).tallerChild();
        Node<E> node = ((AVLNode) parent).tallerChild();
        if (parent.isLeftChild()){      //L
            if (node.isLeftChild()){    //LL
                rotateRight(grand);
            }else {             //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {                         //R
            if (node.isLeftChild()){    //RL
                rotateRight(parent);
                rotateLeft(grand);
            }else {             //RR
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ( (node = node.parent) != null){
            if (isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢复平衡
                rebalance(node);
                //恢复平衡后
                break;
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> parent, Node<E> grand, Node<E> child) {
        super.afterRotate(parent, grand, child);
        //更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }
    //通过平衡因子计算是否平衡
    public boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }
    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }
    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = (left == null) ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = (left == null) ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        //返回子树中高度更高的节点
        public Node<E> tallerChild() {
            int leftHeight = (left == null) ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;    //如果左子节点和右子节点的高度相等，返回和父节点中方向一致的节点
        }
        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null){
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
