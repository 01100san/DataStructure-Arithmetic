package com.mysite;

/**
 * ClassName: Person
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/21 21:27
 * version 1.0
 */
public class Person implements Comparable<Person> {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person e) {
        return age - e.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

