package com.javapractice.java.designPattern.decorator;

public class LoggingDecorator extends PaymentDecorator{
    public LoggingDecorator(Payment decoratedpayment) {
        super(decoratedpayment);
    }

    @Override
    public void makePayment() {
        logPayment();
        super.makePayment();
    }

    private void logPayment() {
        System.out.println("Logging decorated payment");

    }
}
