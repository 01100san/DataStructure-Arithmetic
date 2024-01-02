package com.mysite.trie;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * ClassName: Trie
 * Package: com.mysite.trie
 * Description
 *  Trie->前缀查找树
 * @Author zhl
 * @Create 2024/1/2 14:02
 * version 1.0
 */
public class Trie<V> {
    private int size = 0;
    private Node<V> root;
    public int size(){
        return size;
    }
    public boolean isEmpty (){
        return size == 0;
    }
    public void clear (){
        size = 0;
        root = null;
    }
    public V get(String key){
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }
    public boolean contains(String key){
        Node<V> node = node(key);
        return node != null && node.word;
    }
    public V add(String key, V value){
        keyCheck(key);

        //创建根节点
        if (root == null) {
            root = new Node<>(null);
        }
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);     //d o g
            boolean emptyChildren = node.children == null;
            Node<V> childNode =emptyChildren ? null : node.children.get(c);
            if (childNode == null){
                childNode = new Node<>(node);
                childNode.character = c;
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c,childNode);
            }
            node = childNode;
        }
        //不存在,新增一个单词
        if (!node.word){
            node.word = true;
            node.value = value;
            size ++;
            return null;
        }

        //存在,覆盖
        V oldVal = node.value;
        node.value = value;
        return oldVal;
    }
    public V remove(String key){
        keyCheck(key);
        //查看是否是一个单词
        Node<V> node = node(key);
        if (node == null || !node.word) return null;
        size--;

        V oldVal = node.value;
        //如果有子节点
        if (node.children != null && !node.children.isEmpty()){
            node.word = false;
            node.value = null;
            return oldVal;
        }
        //如果没有子节点
        //从下往上删
        Node<V> parent = null;
        while ((parent = node.parent) != null){
            parent.children.remove(node.character);
            if (parent.word || !parent.children.isEmpty()) break;
            node = parent;
        }

        return oldVal;
    }
    public boolean starsWith(String prefix){
        /*keyCheck(prefix);
        Node<V> node = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty())   return false;
            char c = prefix.charAt(i);     //d o g
            node = node.children.get(c);
        }
        return true;*/
        return node(prefix) != null;
    }
    private Node<V> node(String key){
        keyCheck(key);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty())   return null;
            char c = key.charAt(i);     //d o g
            node = node.children.get(c);
        }

        return node;
    }
    private void keyCheck(String key){
        if (key == null || key.length() == 0){
            throw new IllegalArgumentException("key must not be empty");
        }
    }
    private static class Node<V> {
        Node<V> parent;
        HashMap<Character,Node<V>> children;
        Character character;
        V value;    //存储的值，在add(String key,V value)中的value
        boolean word;   //判断是否是一个单词

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }

}
