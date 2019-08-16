package com.up360.mi.java2.chapter13._2_8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TrySerializableLinkedList {

    public static void main(String[] args){
        LinkedList<Integer> numbers = new LinkedList<>();
        for(int i=0; i<10; i++){
            numbers.addItem(1+(int)(100.0 * Math.random()));
        }
        System.out.println("\nnumbers list contains:");
        listAll(numbers);

        String filename = "F:/project/Mi/Numbers.bin";
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename));

            objOut.writeObject(numbers);
            objOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        LinkedList<Integer> values = null;
        try {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename));
            values = (LinkedList<Integer>) objIn.readObject();
            objIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nvalues list contains:");
        listAll(values);
    }

    static void listAll(LinkedList<Integer> list){
        Integer number = list.getFirst();
        int count = 0;
        do {
            System.out.printf("%5d", number);
            if(++count % 5 == 0){
                System.out.println();
            }
        } while ((number = list.getNext()) != null);
        Class<String> s;
    }
}
