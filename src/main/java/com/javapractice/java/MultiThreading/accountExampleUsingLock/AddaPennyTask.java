package com.javapractice.java.MultiThreading.accountExampleUsingLock;

public class AddaPennyTask implements Runnable{

    private Account account;

    public AddaPennyTask(Account account)
    {
        this.account = account;
    }

    @Override
    public void run() {
            account.deposit(1);
    }
}
