package com.mysite;


/**
 * ClassName: Key
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/30 17:16
 * version 1.0
 */
public class Key {
    private int value;
    public Key(int value){
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return value+"";
    }
}
