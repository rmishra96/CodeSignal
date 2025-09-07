package com.javapractice.java.designPattern.decorator;

public class FraudDetectionDecorator extends PaymentDecorator{
    public FraudDetectionDecorator(Payment decoratedpayment) {
        super(decoratedpayment);
    }

    @Override
    public void makePayment() {
        addFraudDetection();
        super.makePayment();
    }

    private void addFraudDetection() {
        System.out.println("Applying fraud detection checks");
    }
}
