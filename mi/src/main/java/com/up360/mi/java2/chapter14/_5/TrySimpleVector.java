package com.up360.mi.java2.chapter14._5;

import java.util.Iterator;
import java.util.Vector;

public class TrySimpleVector {
    public static void main(String[] args){
        Vector<String> names = new Vector<String>();
        String[] firstnames = {"Jack", "Jill", "John", "Joan", "Jeremiah", "Josephine"};
        for(String firstname : firstnames){
            names.add(firstname);
        }

        for(String name : names){
            System.out.println(name);
        }

        Iterator<String> iter = names.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        for(int i=0; i<names.size(); i++){
            System.out.println(names.get(i));
        }

        int namesMax = names.capacity();
        names.ensureCapacity(150);
        names.trimToSize();
        names.add("jim");

    }
}
