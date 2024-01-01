package com.mysite;

import com.mysite.map.HashMap;
import com.mysite.map.Map;
import org.junit.Test;
import sun.security.provider.ConfigFile;

import java.util.Arrays;

/**
 * ClassName: TestHashMap
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/30 15:37
 * version 1.0
 */
public class TestHashMap {
    @Test
    public void test1(){
        Person p1 = new Person(10,10.3f,"java");
        Person p2 = new Person(10,10.3f,"java");
        HashMap<Object, Integer> map = new HashMap<>();
        map.put(p1,1);
        map.put(p2,2);
        map.put("jack",3);
        map.put("rose",4);
        map.put("jack",5);
        map.put(null,6);

        System.out.println(map.size());

        /*map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });*/

        System.out.println(map.containsKey(p1));
        System.out.println(map.containsKey(null));
        System.out.println(map.containsValue(6));
        System.out.println(map.containsValue(1));

        map.print();

        /*System.out.println(map.remove("jack"));
        System.out.println(map.size());
        System.out.println(map.get("jack"));*/

        /*System.out.println(map.get("jack"));
        System.out.println(map.get("rose"));
        System.out.println(map.get(null));
        System.out.println(map.get(p2));*/

    }
    @Test
    public void test2(){
        HashMap<Object, Integer> map = new HashMap<>();
        for (int i = 1; i <= 19; i++) {
            map.put(new Key(i),i);
        }

        System.out.println(map.size());
        /*map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });*/
        map.print();
    }
    @Test
    public void test3(){
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i + "",i);
        }
        /*map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.print(key + "_" + value + "  ");
                return false;
            }
        });*/
        System.out.println();
        map.print();
    }
    @Test
    public void test4(){
        HashMap<Person, Integer> map = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            map.put(new Person(i,12f,"name " + i),i);
        }
        map.print();
    }
}
