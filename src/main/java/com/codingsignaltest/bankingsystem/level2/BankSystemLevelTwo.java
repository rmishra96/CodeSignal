package com.codingsignaltest.bankingsystem.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*The banking system should support ranking accounts based on the total value of transactions
 The banking system should support ranking accounts based on total number of transactions.
 1, TOP_ACTIVITY <timestamp> <n> return the top n accounts with the highest total value of transactions in descending order.
  A string representing an array of accounts and transaction values in this format "<accountId1>(<transactionValue1>)".
  * Total value of transactions is defined as the sum of all transactions for an account
  (regardless of how the transaction affects account balance), including the amount of money deposited, withdrawn,
  and/or successfully transferred (transfers will be introduced on level
  3, so you can ignore them for now). *
If less than n accounts exist in the system, return all active accounts (in the described format).*/
public class BankSystemLevelTwo {

    private HashMap<String,Integer> accounts;
    private HashMap<String, Integer> transactionAccounts;

    public BankSystemLevelTwo() {
        accounts = new HashMap<>();
        transactionAccounts = new HashMap<>();
    }
    // Create account with timestamp and accountId
    public boolean createAccount(String timestamp, String accountId){
        if(accounts.containsKey(accountId)) {
            return false;
        }

        accounts.put(accountId,0);
        transactionAccounts.put(accountId,0);
        return true;

    }

    public String deposit(String timestamp, String accountId, int amount){
        if(!accounts.containsKey(accountId)){
            return " ";
        }
        accounts.put(accountId, accounts.get(accountId) + amount);
        transactionAccounts.put(accountId,transactionAccounts.get(accountId) + amount);
        return String.valueOf(accounts.get(accountId));


    }

    public String pay(String accountID,String timestamp, int amount){
        if(!accounts.containsKey(accountID)){
            return " ";
        }
       int currentBalance = accounts.get(accountID);
        if(currentBalance < amount){
            return " ";
        }
        int newBalance = currentBalance -amount;
        accounts.put(accountID,newBalance);
        transactionAccounts.put(accountID,transactionAccounts.get(accountID) + amount);
        return String.valueOf(accounts.get(accountID));
    }

    // Top Activity <timestamp> <n>
    public String topActivity(String timestamp,int n){
        List<Map.Entry<String,Integer>> sortList = new ArrayList<>(transactionAccounts.entrySet());
        sortList.sort( (a,b) -> b.getValue().compareTo(a.getValue()) );

        StringBuilder result = new StringBuilder();
        int count = Math.min(n, sortList.size());
        for(int i =0 ;i < count; i++){
            Map.Entry<String, Integer> entry = sortList.get(i);
            result.append(entry.getKey()).append("(").append(entry.getValue()).append(")");
            if (i < count - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        BankSystemLevelTwo bankSystem = new BankSystemLevelTwo();
        System.out.println(bankSystem.createAccount("2023-10-01T10:00:00Z", "acc123"));
        System.out.println(bankSystem.createAccount("2025-06-25T12:01:00", "acc456"));

        System.out.println(bankSystem.deposit("2023-10-01T10:00:00Z", "acc123", 1000));
        System.out.println(bankSystem.pay("acc123", "2023-10-01T10:05:00Z", 300));
        System.out.println(bankSystem.pay("acc123", "2023-10-01T10:05:00Z", 200));

        System.out.println(bankSystem.topActivity("2023-10-01T11:00:00Z", 2));
    }

}
