package com.mysite;

/**
 * ClassName: Person
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2024/1/1 21:51
 * version 1.0
 */
public class Person implements Comparable<Person>{
    private String name;
    private int boneBreak;

    public Person(String name, int boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    @Override
    public int compareTo(Person person) {
        return this.boneBreak - person.boneBreak;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", boneBreak=" + boneBreak +
                '}';
    }
}
