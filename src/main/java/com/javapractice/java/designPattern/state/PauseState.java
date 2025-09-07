package com.javapractice.java.designPattern.state;

import java.util.Scanner;

public class PauseState implements State{
    @Override
    public String doAction(Game game) {
        System.out.println("####\n\n");
        System.out.println("Pause Screen:\n");
        System.out.println("Where do you want to go?");
        System.out.println("1. Main Menu\n2. Quit Game\n");
        String nextState;
        int ip = new Scanner(System.in).nextInt();
        switch (ip){
            case 1:
                nextState = "MainMenuScreen";
                break;
            case 2:
                nextState = "GameEndScreen";
                break;
            default:
                nextState = "MainMenuScreen";
                break;
        }
        return nextState;
    }
}
