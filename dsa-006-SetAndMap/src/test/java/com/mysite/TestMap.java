package com.mysite;

import com.mysite.map.Map;
import com.mysite.map.TreeMap;
import org.junit.Test;

/**
 * ClassName: TestMap
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/28 21:49
 * version 1.0
 */
public class TestMap {
    @Test
    public void test1(){
        Map<String, Integer> map = new TreeMap<>();
        map.put("class",2);
        map.put("public",5);
        map.put("test",3);
        map.put("publix",0);
        System.out.println(map.size());
        map.traversal(new Map.Visitor<String, Integer>(){
            public boolean visit(String key, Integer value){
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
    @Test
    public void test2(){
        Map<String, Integer> map = new TreeMap<>();
        map.put("class",2);
        map.put("public",5);
        map.put("test",3);
        map.put("publix",0);
        System.out.println(map.size());
        map.traversal(new Map.Visitor<String, Integer>() {
            public boolean visit(String key, Integer value){
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
}
