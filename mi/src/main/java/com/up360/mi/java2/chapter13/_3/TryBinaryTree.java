package com.up360.mi.java2.chapter13._3;

public class TryBinaryTree {
    public static void main(String[] args){
        int[] numbers = new int[30];
        for(int i=0; i<numbers.length; i++){
            numbers[i] = (int)(1000.0 * Math.random());
        }

        int count = 0;
        System.out.println("Original values are:");
        for(int number : numbers){
            System.out.printf("%6d", number);
            if(++count%6 == 0){
                System.out.println();
            }
        }
        BinaryTree<Integer> tree = new BinaryTree<>();
        for(int number : numbers){
            tree.add(number);
        }

        LinkedList<Integer> values = tree.sort();
        count = 0;
        System.out.println("\nSorted values are:");
        for(Integer value : values){
            System.out.printf("%6d", value);
            if(++count%6 == 0){
                System.out.println();
            }
        }

        String[] words = {"vacillate", "procrastinate", "arboreal", "syzygy",
                "xenocracy", "zygote", "mephitic", "soporific",
                "grisly", "gristly"};

        System.out.println("\nOriginal word sequence:");
        for(String word : words){
            System.out.printf("%-15s", word);
            if(++count%5 == 0){
                System.out.println();
            }
        }
        BinaryTree<String> cache = new BinaryTree<>();
        for(String word : words){
            cache.add(word);
        }

        LinkedList<String> sortedWords = cache.sort();
        System.out.println("\nSorted word sequence:");
        count = 0;
        for(String word : sortedWords){
            System.out.printf("%-15s", word);
            if(++count%5 == 0){
                System.out.println();
            }
        }

        BinaryTree<Person> people = new BinaryTree<>();
        BinaryTree<Manager> managers = new BinaryTree<>();


    }
}
