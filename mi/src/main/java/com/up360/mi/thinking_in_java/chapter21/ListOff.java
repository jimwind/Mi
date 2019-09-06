package com.up360.mi.thinking_in_java.chapter21;

public class ListOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public ListOff(){

    }
    public ListOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "("  +
                (countDown > 0 ? countDown : "LiftOff!") + ").";
    }
    @Override
    public void run() {
        System.out.println("ListOff id "+Thread.currentThread().getId());
        while (countDown-- > 0){
            System.out.print(status());
            Thread.yield();
        }
    }
}
