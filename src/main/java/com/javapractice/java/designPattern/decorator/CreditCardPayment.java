package com.javapractice.java.designPattern.decorator;

public class CreditCardPayment implements Payment {

    @Override
    public void makePayment() {
        System.out.println("Processing Payment through Credit Card");
    }

}
