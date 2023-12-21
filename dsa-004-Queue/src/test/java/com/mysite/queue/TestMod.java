package com.mysite.queue;

import org.junit.Test;

/**
 * ClassName: TestMod
 * Package: com.mysite.queue
 * Description
 *  取模运算的优化
 * @Author zhl
 * @Create 2023/12/20 20:22
 * version 1.0
 */
public class TestMod {
    @Test
    public void test1(){
        int n = 10;
        int m = 9;

        /*if (m >= n){
            System.out.println(m - n);
        }else {
            System.out.println(m);
        }*/

        // m > 0, n >= 0, n < 2m
        System.out.println(n - (n >= m ? m : 0));

        System.out.println(n % m);
    }
}
