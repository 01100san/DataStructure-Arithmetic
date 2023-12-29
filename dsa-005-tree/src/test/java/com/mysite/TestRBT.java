package com.mysite;

import com.mysite.printer.BinaryTrees;
import com.mysite.redblacktree.RBTree;
import org.junit.Test;

import java.util.Map;

/**
 * ClassName: TestRBT
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/27 21:52
 * version 1.0
 */
public class TestRBT {
    @Test
    public void test1(){
        Integer[] data = new Integer[]{
                85, 71, 95, 52, 47, 18, 14, 83, 50, 86, 69, 40, 54, 3, 70, 24, 25, 96, 19, 80
        };
        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);
    }
}
