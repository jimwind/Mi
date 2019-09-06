package com.up360.mi.java2.chapter16;

//银行类：业务类型
public class Bank {
    /*synchronized*/ public void doTransaction(Transaction transaction){


        switch (transaction.getTransactionType()) {
            case Transaction.CREDIT: {
                //只需要限制同一账户在同一时间操作
                synchronized (transaction.getAccount()) {
                    //对应账户初始余额
                    int balance = transaction.getAccount().getBalance();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    balance += transaction.getAmount();
                    transaction.getAccount().setBalance(balance);
                }
                break;
            }
            case Transaction.DEBIT:{
                synchronized (transaction.getAccount()) {
                    //对应账户初始余额
                    int balance = transaction.getAccount().getBalance();
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                    balance -= transaction.getAmount();
                    transaction.getAccount().setBalance(balance);
                }
                break;
            }
            default:{
                System.out.println("Invalid transaction");
                System.exit(1);
            }
        }

    }
}
