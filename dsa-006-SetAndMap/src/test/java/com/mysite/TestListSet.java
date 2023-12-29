package com.mysite;

import com.mysite.set.ListSet;
import com.mysite.set.Set;
import org.junit.Test;

import java.util.List;

/**
 * ClassName: TestSet
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/28 16:20
 * version 1.0
 */
public class TestListSet {
    @Test
    public void test1() {
        Set<Integer> list = new ListSet<>();
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);

        list.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
