package com.mysite._01_timecomplexity;

import org.junit.Test;

/**
 * ClassName: TimeComplex
 * Package: com.mysite.timecomplexity
 * Description
 *
 * @Author zhl
 * @Create 2023/10/28 21:57
 * version 1.0
 */
public class TimeComplex {
    private static int n = 10;
    @Test
    public void test1(){
        //1 + 3n
        for (int i = 0; i < n; i++) {
            System.out.println("test");
        }
    }
    @Test
    public void test2(){
        // 1 + 2n + n * (1 + 3n)
        // 3n^2 + 3n + 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }

    @Test
    public void test3(){
        //1 + 2n + n * (1 + 45)
        //48n + 1
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.println("test");
            }
        }
    }
    @Test
    public void test4(){
        // log2(n)
        while ((n = n / 2) > 0){
            System.out.println("test");
        }
    }
    @Test
    public void test5(){
        // log5(n)
        while ((n = n / 2) > 0){
            System.out.println("test");
        }
    }
    @Test
    public void test6(){
        // log2(n)
        for (int i = 1; i < n; i+=i) {  //i += i -> i = i*2
            //1 + 3n
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }
}
