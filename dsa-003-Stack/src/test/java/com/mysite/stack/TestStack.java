package com.mysite.stack;

import org.junit.Test;
import sun.security.provider.ConfigFile;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: TestStack
 * Package: com.mysite.stack
 * Description
 *  测试栈
 * @Author zhl
 * @Create 2023/12/18 20:07
 * version 1.0
 */
public class TestStack {
    @Test
    public void test1(){
        Stack<Integer> stack = new Stack<>();

        stack.push(12);
        stack.push(88);
        Integer top = stack.top();
        //System.out.println(top);

        System.out.println(stack.pop());
        System.out.println("**************");
        while (! stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}
