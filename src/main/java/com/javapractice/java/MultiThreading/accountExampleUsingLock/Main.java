package com.javapractice.java.MultiThreading.accountExampleUsingLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Account account = new Account();
        for(int i =0 ; i < 100000 ;i++){
            executorService.execute(new AddaPennyTask(account));
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){

        }
        System.out.println(account.getBalance());
    }
}
