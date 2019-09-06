package com.up360.mi.java2.chapter16;

//指定一个完整的业务
public class Transaction {
    public static final int DEBIT = 0;
    public static final int CREDIT = 1;
    public static String[] types = {"Debit", "Credit"};


    private Account account;//账户
    private int amount;//当前业务总额
    private int transactionType;//当前业务类型

    public Transaction(Account account, int transactionType, int amount) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public int getAmount() {
        return amount;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public String toString() {
        return types[transactionType] + "A//C" + ":$" + amount;
    }
}
