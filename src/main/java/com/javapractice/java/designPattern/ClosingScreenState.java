package com.javapractice.java.designPattern;

public class ClosingScreenState implements State{
    @Override
    public String doAction(Game game) {
        System.out.println("####\n\n");
        System.out.println("Game Ended!!");
        System.out.println("\n\n####");
        return null;
    }
}