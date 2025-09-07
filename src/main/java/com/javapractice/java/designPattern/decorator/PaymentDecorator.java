package com.javapractice.java.designPattern.decorator;

public abstract class PaymentDecorator implements Payment{
    protected Payment decoratedpayment;

    public PaymentDecorator(Payment decoratedpayment) {
        this.decoratedpayment = decoratedpayment;
    }

    @Override
    public void makePayment() {
        decoratedpayment.makePayment();
    }
}
