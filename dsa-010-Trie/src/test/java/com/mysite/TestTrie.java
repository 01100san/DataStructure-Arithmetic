package com.mysite;

import com.mysite.trie.Trie;
import org.junit.Test;

import java.util.Timer;

/**
 * ClassName: TestTrie
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2024/1/2 14:07
 * version 1.0
 */
public class TestTrie {
    @Test
    public void test1(){
        Trie<Integer> trie = new Trie<>();
        trie.add("a",1);
        trie.add("b",2);
        trie.add("c",3);
        trie.add("d",4);
        trie.add("e",5);
        trie.add("ass",8);
        trie.add("sss",9);
        trie.add("六六六",10);

        System.out.println(trie.size());
        System.out.println(trie.get("c"));
        System.out.println(trie.get("六六六"));
        System.out.println(trie.starsWith("www"));
        System.out.println(trie.starsWith("s"));
        System.out.println(trie.contains("sss"));
        System.out.println();
        System.out.println(trie.remove("a"));
        System.out.println(trie.remove("六六六"));
        System.out.println(trie.size());
    }
}
