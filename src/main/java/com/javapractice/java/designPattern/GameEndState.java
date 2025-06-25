package com.javapractice.java.designPattern;

import java.util.Scanner;

public class GameEndState implements State{
    @Override
    public String doAction(Game game) {
        System.out.println("####\n\n");
        System.out.println("Game End Screen:");
        System.out.println("Choose any option:\n");
        System.out.println("1. Quit Game\n2. Replay");
        int option = new Scanner(System.in).nextInt();
        String nextScreen = "ClosingScreen";
        switch (option){
            case 1:
                nextScreen = "ClosingScreen";
                break;
            case 2:
                nextScreen = "NewGameScreen";
                break;
        }

        return nextScreen;
    }
}
