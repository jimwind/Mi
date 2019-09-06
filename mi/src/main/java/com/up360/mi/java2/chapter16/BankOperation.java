package com.up360.mi.java2.chapter16;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BankOperation {
    public static void main(String[] args){
        {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS");
            System.out.println(sdf.format(d));
        }
        int[] initialBalance = {500, 800};//$
        int[] totalCredits = new int[initialBalance.length];//记入贷方
        int[] totalDebits = new int[initialBalance.length];//记入借方
        int transactionCount = 20;//业务数量

        Bank theBank = new Bank();
        Clerk clerk1 = new Clerk(theBank);
        Clerk clerk2 = new Clerk(theBank);
        //注意，只有一个账户，有两个业务员
        Account[] account = new Account[initialBalance.length];
        for(int i=0; i<initialBalance.length; i++){
            account[i] = new Account(i+1, initialBalance[i]);
            totalCredits[i] = totalDebits[i] = 0;
        }

        //主线程和两个
        Thread clerk1Thread = new Thread(clerk1);
        Thread clerk2Thread = new Thread(clerk2);

        clerk1Thread.setDaemon(true);
        clerk2Thread.setDaemon(true);

        clerk1Thread.start();
        clerk2Thread.start();

        Random random = new Random();
        Transaction transaction;
        int amount = 0;
        int select = 0;
        //总业务数
        for(int i=1; i<= transactionCount; i++){
            select = random.nextInt(account.length);
            //业务员1做CREDIT
            //每次业务额从50至75不等
            amount = 50 + random.nextInt(26);
            transaction = new Transaction(account[select], Transaction.CREDIT, amount);
            //总存款
            totalCredits[select] += amount;

            //很忙 主线程阻塞 但是clerk1线程仍然在跑
//            while (clerk1.isBusy()){
//                try {
//                    Thread.sleep(25);
//                } catch (InterruptedException e){
//                    System.out.println(e);
//                }
//            }
            clerk1.doTransaction(transaction);


            select = random.nextInt(account.length);
            //业务员2做 DEBIT
            amount = 30 + random.nextInt(31);
            transaction = new Transaction(account[select], Transaction.DEBIT, amount);
            //总贷款
            totalDebits[select] += amount;
//            while (clerk2.isBusy()){
//                try {
//                    Thread.sleep(25);
//                } catch (InterruptedException e){
//                    System.out.println(e);
//                }
//            }
            clerk2.doTransaction(transaction);

        }
//        while (clerk1.isBusy() || clerk2.isBusy()){
//            try {
//                Thread.sleep(25);
//            } catch (InterruptedException e){
//                System.out.println(e);
//            }
//        }
        clerk1.isBusy();
        clerk2.isBusy();

        {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS");
            System.out.println(sdf.format(d));
        }
        for(int i=0; i<account.length; i++) {
            System.out.println(
                    "Account number: "+account[i].getAccountNumber()+"\n" +
                    "Original balance : $" + initialBalance[i] + "\n" +
                            "Total credits    : $" + totalCredits[i] + "\n" +
                            "Total debits     : $" + totalDebits[i] + "\n" +
                            "Final balance    : $" + account[i].getBalance() + "\n" +
                            "Should be        : $" + (initialBalance[i] + totalCredits[i] - totalDebits[i])
            );
        }
    }
}
