package com.up360.mi.java2.chapter16;


public class Account {
    private int accountNumber;//账目
    private int balance;//结平（账目）
    public Account(int accountNumber, int balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String toString(){
        return "A//C No. "+accountNumber +" : $ "+balance;
    }
}
