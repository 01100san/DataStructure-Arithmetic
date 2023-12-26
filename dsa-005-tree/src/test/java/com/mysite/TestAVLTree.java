package com.mysite;

import com.mysite.avltree.AVLTree;
import com.mysite.binarysearchtree.BST;
import com.mysite.printer.BinaryTrees;
import org.junit.Test;

/**
 * ClassName: TestAVLTree
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/24 21:03
 * version 1.0
 */
public class TestAVLTree {
    @Test
    public void test1(){
        Integer[] data = new Integer[]{
                7,4,2,1,3,5,9,8,11,10,12
                //7,4,9,2,5
        };
        AVLTree<Integer> bst = new AVLTree<>();

        for (int i = 0; i < data.length; i++) {
            //bst4.add(data[i]);
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        bst.remove(2);
        System.out.println("------------------------------------------------------------------------------------");
        BinaryTrees.println(bst);
        bst.remove(1);
        System.out.println("------------------------------------------------------------------------------------");
        BinaryTrees.println(bst);
    }
}


