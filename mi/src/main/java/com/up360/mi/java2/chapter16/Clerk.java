package com.up360.mi.java2.chapter16;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Clerk implements Runnable {
    //哪个银行的职员！
    public Clerk(Bank theBank){
        this.theBank = theBank;
//        inTray = null;//默认无业务
    }

    //这是在主线程中接收
    //银行职员接收业务
    synchronized public void doTransaction(Transaction transaction){
        while (inTray.size() >= maxTransactions){
            try {
                wait();
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
        inTray.add(transaction);
        notifyAll();
    }
    //这是在子线程中运行
    @Override
    synchronized public void run() {
        while (true){
            while (inTray.size() == 0){//没有业务，休息
                try {
                    wait();//Thread.sleep(150);
                } catch (InterruptedException e){
                    System.out.println(e);
                }
            }
            //所属银行的业务
            theBank.doTransaction(inTray.remove(0));
//            inTray = null;
            notifyAll();
        }
    }
    //这是在主线程中判断
    synchronized public void isBusy(){
        while (inTray.size() != 0){
            try {
                wait();
            } catch (InterruptedException e){
                System.out.println(e);
            }
            return;
        }
    }

    private Bank theBank;
//    private Transaction inTray;
    private List<Transaction> inTray = Collections.synchronizedList(new LinkedList<Transaction>());
    private int maxTransactions = 8;
}
