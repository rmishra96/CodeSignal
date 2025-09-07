package com.javapractice.java.designPattern.state;

import java.util.Scanner;

public class StatsState implements State{
    @Override
    public String doAction(Game game) {
        String nextState;
        System.out.println("####\n\n");
        System.out.println("Stats Screen:\n");
        System.out.println("Place holder for showing stats of the game!\n");
        System.out.println("Choose any key to return to Main Menu: \n");
        new Scanner(System.in);
        nextState = "MainMenuScreen";

        return nextState;
    }
}