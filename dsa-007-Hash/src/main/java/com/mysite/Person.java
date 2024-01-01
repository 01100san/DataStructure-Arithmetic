package com.mysite;

import java.util.Objects;

/**
 * ClassName: Person
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/29 20:23
 * version 1.0
 */
public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
        return hashCode;
        //return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        //内存地址
        if (this == o)  return true;
        if (o == null || o.getClass() != getClass())  return false;
        Person person = (Person) o;
        //比较成员变量
        return age == person.age
                && height == person.height
                && Objects.equals(person.name,name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                ", name='" + name + '\'' +
                '}';
    }
}
