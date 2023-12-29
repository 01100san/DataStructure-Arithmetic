package com.mysite.set;

import com.mysite.list.LinkedList;
import com.mysite.list.List;
import sun.misc.OSEnvironment;

/**
 * ClassName: ListSet
 * Package: com.mysite.set
 * Description
 *
 * @Author zhl
 * @Create 2023/12/28 16:07
 * version 1.0
 */
public class ListSet<E> implements Set<E>{
    List<E> list = new LinkedList<>();
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {

        int index = list.indexOf(element);
        if (index != -1){
            list.set(index,element);
        }else {
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        list.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))) {
                return;
            }
        }
    }
}
