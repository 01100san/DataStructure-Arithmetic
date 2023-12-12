package com.mysite;

import com.mysite._02_dynamic_array.ArrayList;
import com.mysite._02_dynamic_array.Person;
import org.junit.Test;

/**
 * ClassName: TestDynamicArray
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/12 19:04
 * version 1.0
 */
public class TestDynamicArray {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(15);
        list.add(15);
        list.add(15);
        list.add(15);
        list.add("hello");


        //扩容
        list.add(99);

        //System.out.println(list.remove(5));

        //list.set(5,99);
        //list.add(3,88);
        //System.out.println(list.isEmpty());
        /*System.out.println( "按照索引 删除的元素：" + list.remove(list.size() - 1));
        System.out.println("查看是否包含的元素：" + list.contains(13));
        System.out.println("数组的长度" + list.size());
        System.out.println("按照指定索引获取数组中的元素：" + list.get(5));
        System.out.println("按照指定元素 删除的索引：" + list.remove(Integer.valueOf(12)));
        System.out.println("按照指定元素 删除的索引：" + list.remove("hello"));*/
        System.out.println(list);
    }
    @Test
    public void test2(){
        java.util.ArrayList<Object> list = new java.util.ArrayList<>();
        /*list.add(2,"22");*/
        list.add(21);
        list.add(12);
        /*list.add(1,"ss");*/
        //list.set(1,99);
        String[] strs = new String[]{"s","w","d"};

        //list.remove(2);
        System.out.println(list);
    }

    @Test
    public void test3(){
        ArrayList<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setAge(12);
        person.setName("sss");

        list.add(person);
        list.add("ss");
        list.add(21);
        list.add(new String[]{"s","w","d"});

        System.out.println(list);
    }

}
