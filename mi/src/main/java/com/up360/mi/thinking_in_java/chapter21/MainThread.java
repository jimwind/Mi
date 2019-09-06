package com.up360.mi.thinking_in_java.chapter21;

public class MainThread {
    public static void main(String[] args){
        System.out.println("MainThread id "+Thread.currentThread().getId());
        ListOff launch = new ListOff(10);
//        launch.run(); 以这种方式运行，在run中取到的线程id与当前主线程一致
        new Thread(launch).start();
        System.out.println("Waiting for LiftOff");
    }
}
