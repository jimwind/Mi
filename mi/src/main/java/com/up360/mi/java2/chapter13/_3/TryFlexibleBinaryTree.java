package com.up360.mi.java2.chapter13._3;

public class TryFlexibleBinaryTree {
    public static void main(String[] args){
        BinaryTree<Manager> people = new BinaryTree<>();
        Manager[] managers = {
                new Manager("Jane", 1),
                new Manager("Joe",3),
                new Manager("Freda", 3),
                new Manager("Albert", 2)};

        for (Manager manager : managers){
            people.add(manager);
            System.out.println("Added "+manager);
        }
        System.out.println();
        listAll(people.sort());
    }

    public static void listAll(LinkedList<?> list){
        for(Object obj : list){
            System.out.println(obj);
        }
    }
}
