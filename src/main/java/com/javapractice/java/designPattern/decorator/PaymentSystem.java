package com.javapractice.java.designPattern.decorator;

public class PaymentSystem {
    public static void main(String[] args) {

        Payment paymentSystem = new CreditCardPayment();
        System.out.println("Basic Credit Card Payment");

        paymentSystem.makePayment();

        System.out.println("*************************************");

        Payment fraudProtectedPayment = new FraudDetectionDecorator(paymentSystem);

        System.out.println("Credit Card Payment with Fraud Detections");
        fraudProtectedPayment.makePayment();

        System.out.println("****************************************");
        // PayPal Payment with Fraud Detection and Logging
        Payment payPalPayment = new PayPalPayment();
        Payment payPalWithFeatures = new LoggingDecorator(new FraudDetectionDecorator(payPalPayment));
        System.out.println("PayPal Payment with Fraud Detection and Logging:");
        payPalWithFeatures.makePayment();

    }
}
