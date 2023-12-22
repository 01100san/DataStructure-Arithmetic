package com.mysite;

import com.mysite.binarysearchtree.BinarySearchTree;
import com.mysite.printer.BinaryTrees;
import org.junit.Test;

import java.util.Base64;
import java.util.Comparator;

/**
 * ClassName: TestBinarySearchTree
 * Package: com.mysite
 * Description
 * 测试BinarySearchTree
 * @Author zhl
 * @Create 2023/12/21 21:27
 * version 1.0
 */
public class TestBinarySearchTree {
    @Test
    public void test1(){
        Integer[] data = new Integer[]{
                7,4,2,1,3,5,9,8,11,10,12
        };
        BinarySearchTree<Integer> bst4 = new BinarySearchTree<>();
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

    }

    @Test
    public void test2(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            bst.add((int)(Math.random() * 100));
        }
        BinaryTrees.println(bst);
    }

    @Test
    public void test3(){
        Integer[] data = new Integer[]{
                7,4,9,2,5,8,11,3,12,1
        };
        BinarySearchTree<Person> bst5 = new BinarySearchTree<>(((o1, o2) -> ( -(o1.getAge()-o2.getAge()) )));
        for (int i = 0; i < data.length; i++) {
            bst5.add(new Person(data[i]));
        }
        BinaryTrees.println(bst5);
    }
}
