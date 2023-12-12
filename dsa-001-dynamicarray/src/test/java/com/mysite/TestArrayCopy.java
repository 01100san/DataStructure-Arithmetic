package com.mysite;

import org.junit.Test;

import java.util.Arrays;

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
    public void testArrayCopy(){
        int[] arr1 = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] arr2 = new int[30];
        arr2[0] = 11;
        arr2[1] = 12;
        arr2[2] = 13;
        arr2[3] = 14;
        arr2[4] = 15;
        int len = 0;
        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] == 0){
                len = i;
                break;
            }
        }
        /*System.arraycopy(arr1,2,arr2,5,3);*/
        System.arraycopy(arr1,0,arr2,len,arr1.length);

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

    @Test
    public void testCopyOf(){
        int[] arr = new int[]{1,2,3,4,5};
        int[] newArray = Arrays.copyOf(arr, arr.length);
        for (int i : newArray) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : arr){
            System.out.print(i + " ");
        }
    }
}
