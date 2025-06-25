package com.javapractice.java.designPattern;

import java.util.Scanner;

public class MainMenuState implements State{
    @Override
    public String doAction(Game game) {
        System.out.println("####\n\n");
        System.out.println("What do you want to do?\n");
        System.out.println("1. New Game\n2. Stats");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();
        String nextScreen;

        switch (selection){
            case 1:
                nextScreen = "NewGameScreen";
                break;
            case 2:
                nextScreen = "StatsScreen";
                break;
            default:
                nextScreen = "MainMenuScreen";
        }

        return nextScreen;
    }
}
