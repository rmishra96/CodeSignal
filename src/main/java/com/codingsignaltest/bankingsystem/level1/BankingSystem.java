package com.codingsignaltest.bankingsystem.level1;

/*
* Your task is to implement a simplified version of a banking system.
* Plan your design according to the level specifications below: •
*  Level 1: The banking system should support creating new accounts and depositing money into and withdrawing/paying money from accounts.
* 1, CREATE_ACCOUNT<timestamp><accountId>, returns true if not present and create account, false otherwise
* 2, DEPOSIT <timestamp><accountId><amount>, deposit given amount of money to the specific account.
*  returns a string representing total money in the account (balance). If account does not exist, return empty string.
* 3, PAY <timestamp> <accountId> <amount>, withdraw from the account.
*  returns a string representing account balance after processing the query.
* If account does not exist or insufficient fund, return empty string. -- in java
*
*
* */

import java.util.HashMap;

public class BankingSystem {
    private HashMap<String, Integer> accounts;

    public BankingSystem(){
        accounts = new HashMap<>();
    }

    // create Account with timestamp, accountId
    public boolean createAccount(String timestamp,String accountId){
        if(accounts.containsKey(accountId)){
            return false; // Account already exists
        }
        accounts.put(accountId,0);
        return true;
    }
    // Deposit money into accountid, amount, timestamp
    public String deposit(String timestamp, String accountId, int amount) {
        if(!accounts.containsKey(accountId)) {
            return "Account does not exist";
        }
        int newBalance = accounts.get(accountId) + amount;
        accounts.put(accountId, newBalance);
        return "Deposit successful. New balance: " + newBalance;
    }

    // Pay <timestamp>, <accountId>, <amount>
    public String pay(String timestamp, String accountId, int amount) {
       if(!accounts.containsKey(accountId)){
           return "Account does not exist";
       }

       int currentBalance = accounts.get(accountId);
       if(currentBalance < amount) {
           return "Insufficient funds";
       }
       int newBalance = currentBalance - amount;
       accounts.put(accountId, newBalance);
       return String.valueOf(newBalance);
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        System.out.println(bankingSystem.createAccount("2023-10-01T10:00:00Z", "acc123"));
        System.out.println(bankingSystem.createAccount("2025-06-25T12:01:00", "acc123"));

        System.out.println(bankingSystem.deposit("2023-10-01T10:00:00Z", "acc123", 1000));
        System.out.println(bankingSystem.pay("2023-10-01T10:05:00Z", "acc123", 300));
        System.out.println(bankingSystem.pay("2023-10-01T10:05:00Z", "acc123", 200));
    }

}
