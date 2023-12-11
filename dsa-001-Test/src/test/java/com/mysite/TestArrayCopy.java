package com.mysite;

import org.junit.Test;

/**
 * ClassName: TestArrayCopy
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/11 22:36
 * version 1.0
 */
public class TestArrayCopy {
    @Test
    public void test1(){
        int[] arr1 = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] arr2 = new int[]{11,12,13,14,15,16,17,18,19,20};

        System.arraycopy(arr1,2,arr2,5,3);

        for (int i = 0; i < arr1.length; i++) {
            if (i != 0) System.out.print(", ");
            System.out.print(arr1[i]);
        }
        System.out.println();
        for (int i = 0; i < arr2.length; i++) {
            if (i != 0) System.out.print(", ");
            System.out.print(arr2[i]);
        }
    }
}
