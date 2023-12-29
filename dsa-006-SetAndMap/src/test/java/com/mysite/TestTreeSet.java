package com.mysite;

import com.mysite.map.Map;
import com.mysite.map.TreeMap;
import com.mysite.set.Set;
import com.mysite.set.TreeSet;
import org.junit.Test;
import sun.security.provider.ConfigFile;

import java.lang.annotation.Target;

/**
 * ClassName: TestTreeSet
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/29 14:30
 * version 1.0
 */
public class TestTreeSet {
    @Test
    public void test1(){
        //Map<String, Integer> map = new TreeMap<>();
        Set<String> set = new TreeSet<>();
        set.add("class");
        set.add("public");
        set.add("test");
        set.add("publix");
        System.out.println(set.size());
        set.traversal(new Set.Visitor<String>() {
            @Override
            public boolean visit(String element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
