package com.mysite.stack;

import com.mysite.list.ArrayList;
import com.mysite.list.List;

/**
 * ClassName: Stack
 * Package: com.mysite.stack
 * Description
 *  栈
 * @Author zhl
 * @Create 2023/12/18 19:56
 * version 1.0
 */
public class Stack<E>{
    private List<E> arrayList = new ArrayList<>();
    public boolean isEmpty(){
        return arrayList.isEmpty();
    }
    public int size(){
        return arrayList.size();
    }
    /**
     * 向栈顶添加元素
     * @param element 添加的元素
     */
    public void push(E element){
        arrayList.add(element);
    }

    /**
     * 删除栈顶元素
     * @return 返回栈顶的元素
     */
    public E pop(){
        return arrayList.remove(arrayList.size() - 1);
    }

    /**
     * 获取栈顶元素
     * @return 返回栈顶元素
     */
    public E top(){
        return arrayList.get(arrayList.size() - 1);
    }
}



