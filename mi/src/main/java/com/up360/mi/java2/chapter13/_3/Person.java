package com.up360.mi.java2.chapter13._3;

public class Person implements Comparable<Person> {
    public Person(String name){
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        if(person == this){
            return 0;
        }
        return this.name.compareTo(person.name);
    }
    public String toString(){
        return name;
    }

    protected String name;
}
