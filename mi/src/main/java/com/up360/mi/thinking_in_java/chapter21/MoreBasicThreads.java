package com.up360.mi.thinking_in_java.chapter21;

public class MoreBasicThreads {
    public static void main(String[] args){
        for(int i=0; i<5; i++){
            new Thread(new ListOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
