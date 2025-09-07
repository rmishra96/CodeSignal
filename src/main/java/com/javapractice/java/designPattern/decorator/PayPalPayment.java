package com.javapractice.java.designPattern.decorator;

public class PayPalPayment implements Payment {
    @Override
    public void makePayment() {
        System.out.println("Payment of PayPal");
    }
}
