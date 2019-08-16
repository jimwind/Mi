package com.up360.mi.java2.chapter13._5.demo;

import java.util.ArrayList;
import java.util.List;

public class Test {
    static void test(List<? extends Fruit> fruits) {
        for (Fruit fruit: fruits) {
            fruit.call();
        }
    }

    public static void main(String[] args) {

        List<Apple> apples = new ArrayList<>();
//        List<Fruit> fruits = apples;     //类型转换失败
        apples.add(new Apple());
        Test.test(apples);    //失败
    }
}
