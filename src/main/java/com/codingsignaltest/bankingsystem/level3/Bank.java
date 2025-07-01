package com.codingsignaltest.bankingsystem.level3;

import com.javapractice.java.MultiThreading.accountExampleUsingLock.Account;

import java.util.*;

public class Bank {
    private final TreeMap<Integer, List<ScheduledPayment>> scheduledPayments = new TreeMap<>();
    private final Map<String, ScheduledPayment> paymentLookup = new HashMap<>();
    private int paymentCounter = 1;
    private int lastProcessedTime = 0;

    static class Account {
        String id;
        long balance;
        long transactionValue; // sum of all deposits and payments
        public Account(String id) {
            this.id = id;
            this.balance = 0;
            this.transactionValue = 0;
        }

        public void deposit(long amount) {
            balance += amount;
            transactionValue += amount;
        }

        public boolean pay(long amount) {
            if (amount > balance) return false;
            balance -= amount;
            transactionValue += amount;
            return true;
        }
    }

    private final Map<String, Account> accounts = new HashMap<>();


    public String processQuery(String[] queryParts) {
        String operation = queryParts[0];
        switch (operation) {
            case "CREATE_ACCOUNT":
                return createAccount(queryParts[2]);
            case "DEPOSIT":
                return deposit(queryParts[2], Long.parseLong(queryParts[3]));
            case "PAY":
                return pay(queryParts[2], Long.parseLong(queryParts[3]));
            case "TOP_ACTIVITY":
                return topActivity(Integer.parseInt(queryParts[2]));
            default:
                return "";
        }
    }

    private String createAccount(String id) {
        if (accounts.containsKey(id)) return "false";
        accounts.put(id, new Account(id));
        return "true";
    }

    private String deposit(String id, long amount) {
        Account acc = accounts.get(id);
        if (acc == null) return "";
        acc.deposit(amount);
        return String.valueOf(acc.balance);
    }

    private String pay(String id, long amount) {
        Account acc = accounts.get(id);
        if (acc == null || !acc.pay(amount)) return "";
        return String.valueOf(acc.balance);
    }

    private String topActivity(int n) {
        List<Account> sortedAccounts = new ArrayList<>(accounts.values());
        sortedAccounts.sort((a, b) -> Long.compare(b.transactionValue, a.transactionValue));
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(n, sortedAccounts.size()); i++) {
            Account acc = sortedAccounts.get(i);
            result.add(acc.id + "(" + acc.transactionValue + ")");
        }
        return result.toString();
    }

    private void processScheduledPayments(int timestamp) {
        for (int t = lastProcessedTime + 1; t <= timestamp; t++) {
            List<ScheduledPayment> list = scheduledPayments.get(t);
            if (list != null) {
                for (ScheduledPayment sp : list) {
                    if (!sp.cancelled) {
                        Account acc = accounts.get(sp.accountId);
                        if (acc != null && acc.balance >= sp.amount) {
                            acc.balance -= sp.amount;
                            acc.transactionValue += sp.amount;
                        }
                    }
                }
            }
        }
        lastProcessedTime = Math.max(lastProcessedTime, timestamp);
    }

    public Optional<String> schedulePayment(int timestamp, String accountId, int amount, int delay) {
        processScheduledPayments(timestamp);
        if (!accounts.containsKey(accountId)) return Optional.empty();

        int scheduledTime = timestamp + delay;
        String paymentId = "payment" + paymentCounter++;
        ScheduledPayment sp = new ScheduledPayment(paymentId, accountId, amount, scheduledTime);

        scheduledPayments.computeIfAbsent(scheduledTime, k -> new ArrayList<>()).add(sp);
        paymentLookup.put(paymentId, sp);
        return Optional.of(paymentId);
    }

    public boolean cancelPayment(int timestamp, String accountId, String paymentId) {
        processScheduledPayments(timestamp);
        ScheduledPayment sp = paymentLookup.get(paymentId);
        if (sp == null || sp.cancelled || !sp.accountId.equals(accountId)) return false;
        sp.cancelled = true;
        return true;
    }




}
