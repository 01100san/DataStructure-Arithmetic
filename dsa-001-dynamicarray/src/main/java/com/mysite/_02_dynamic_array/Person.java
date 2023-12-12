package com.mysite._02_dynamic_array;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * ClassName: Person
 * Package: com.mysite._02_dynamic_array
 * Description
 *
 * @Author zhl
 * @Create 2023/12/12 22:12
 * version 1.0
 */
public class Person {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
