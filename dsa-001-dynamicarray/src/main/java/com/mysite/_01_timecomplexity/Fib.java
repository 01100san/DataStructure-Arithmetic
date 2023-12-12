package com.mysite._01_timecomplexity;

import com.mysite.utils.TimeTool;

/**
 * ClassName: Main
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/10/28 21:05
 * version 1.0
 */
public class Fib {
    /*
        0 1 1 2 3 5 8
     */
    public static int fib1(int n){
        if (n <= 1) return n;
        return fib1(n-1) + fib1(n - 2);
    }
    public static int fib2(int n){
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;   // 3
            first = second;             // 2
            second = sum;               // 5
        }
        return second;
    }

    public static void main(String[] args) {
        int n = 48;
        TimeTool.check("fib2", new TimeTool.Task() {
            @Override
            public void execute() {
                fib2(n);
            }
        });
        TimeTool.check("fib1", new TimeTool.Task() {
            @Override
            public void execute() {
                fib1(n);
            }
        });

    }
}
