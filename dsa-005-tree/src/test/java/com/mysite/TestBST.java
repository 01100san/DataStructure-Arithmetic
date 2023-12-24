package com.mysite;

import com.mysite.binarysearchtree.BST;
import com.mysite.printer.BinaryTrees;
import org.junit.Test;

/**
 * ClassName: TestBinarySearchTree
 * Package: com.mysite
 * Description
 * 测试BinarySearchTree
 * @Author zhl
 * @Create 2023/12/21 21:27
 * version 1.0
 */
public class TestBST {
    @Test
    public void test1(){
        Integer[] data = new Integer[]{
                7,4,2,1,3,5,9,8,11,10,12
                //7,4,9,2,5
        };
        BST<Integer> bst4 = new BST<>();
        for (int i = 0; i < data.length; i++) {
            //bst4.add(data[i]);
            bst4.add(data[i]);
        }
        BinaryTrees.println(bst4);
        //bst4.preorderTraversal(); //前序遍历
        //bst4.inorderTraversal();    //中序遍历
        //bst4.postorderTraversal();    //后序遍历
        //bst4.levelOrderTraversal();     //层序遍历
        System.out.println();
        //层序遍历
        bst4.levelOrder(element -> {
            System.out.print("_" + element + "_ ");
        });
        System.out.println();

        //前序遍历
        bst4.preOrder(element -> {
            System.out.print("_" + element + "_ ");
        });
        System.out.println();

        //中序遍历
        bst4.inOrder(element -> {
            System.out.print("_" + element + "_ ");
        });
        System.out.println();

        //后序遍历
        bst4.postOrder(element -> {
            System.out.print("_" + element + "_ ");
        });

        System.out.println();
        System.out.println("------------------------");

        //System.out.println(bst4);

        System.out.println("树的高度：" + bst4.heightByRecu());
        System.out.println("树的高度：" + bst4.heightByIter());
        System.out.println("是否是完全二叉树：" + bst4.isComplete());

    }

    @Test
    public void test2(){
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 30; i++) {
            bst.add((int)(Math.random() * 100));
        }
        BinaryTrees.println(bst);
        System.out.println("树的高度：" + bst.heightByIter());
        System.out.println("是否是完全二叉树：" + bst.isComplete());
    }

    @Test
    public void test3(){
        Integer[] data = new Integer[]{
                7,4,9,2,5,8,11,3,12,1
        };
        BST<Person> bst5 = new BST<>(((o1, o2) -> ( -(o1.getAge()-o2.getAge()) )));
        for (int i = 0; i < data.length; i++) {
            bst5.add(new Person(data[i]));
        }
        BinaryTrees.println(bst5);
    }
    @Test
    public void test4(){
        Integer[] data = new Integer[]{
                7,4,2,1,3,5,9,8,11,10,12
                //7,4,9,2,5
        };
        BST<Integer> bst4 = new BST<>();
        for (int i = 0; i < data.length; i++) {
            //bst4.add(data[i]);
            bst4.add(data[i]);
        }
        BinaryTrees.println(bst4);

        //bst4.remove(5);
        bst4.remove(7);
        BinaryTrees.println(bst4);
    }
}
