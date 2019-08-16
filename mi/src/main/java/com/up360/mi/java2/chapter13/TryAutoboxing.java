package com.up360.mi.java2.chapter13;

public class TryAutoboxing {
    public static void main(String[] args){
        LinkedList<Double> temperatures = new LinkedList<Double>();
        for(int i=0; i<6; i++){
            temperatures.addItem(25.0 * Math.random());
        }

        System.out.printf("%.2f degrees Fahrenheit%n", toFahrenheit(temperatures.getFirst()));

        Double value = null;
        while ((value = temperatures.getNext()) != null){
            System.out.printf("%.2f degrees Fahrenheit%n", toFahrenheit(value));
        }
    }
    public static double toFahrenheit(double temperature){
        return 1.8 * temperature + 32.0;
    }
}
