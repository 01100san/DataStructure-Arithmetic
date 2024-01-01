package com.mysite;

import com.mysite.map.HashMap;
import com.mysite.map.LinkedHashMap;
import com.mysite.map.Map;
import org.junit.Test;

/**
 * ClassName: TestLinkedHashMap
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/31 21:13
 * version 1.0
 */
public class TestLinkedHashMap {
    @Test
    public void test1(){    //有序的遍历
        Map<Object, Integer> map = new LinkedHashMap<>();
        map.put("jack",1);
        map.put("ggb",2);
        map.put("jjb",3);
        System.out.println(map.remove("jjb"));
        map.put("rose",4);
        map.put("lili",5);
        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
    @Test
    public void test2(){    //无序的遍历
        Map<Object, Integer> map = new HashMap<>();
        map.put("jack",1);
        map.put("ggb",2);
        map.put("jjb",3);
        System.out.println(map.remove("jjb"));
        map.put("rose",4);
        map.put("lili",5);
        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
}
