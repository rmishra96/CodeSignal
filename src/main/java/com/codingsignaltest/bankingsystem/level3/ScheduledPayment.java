package com.codingsignaltest.bankingsystem.level3;

public class ScheduledPayment {
    String paymentId;
    String accountId;
    int amount;
    int scheduledTime;
    boolean cancelled;

    ScheduledPayment(String paymentId, String accountId, int amount, int scheduledTime) {
        this.paymentId = paymentId;
        this.accountId = accountId;
        this.amount = amount;
        this.scheduledTime = scheduledTime;
        this.cancelled = false;
    }
}
