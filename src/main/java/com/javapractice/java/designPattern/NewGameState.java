package com.javapractice.java.designPattern;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NewGameState implements State{
    @Override
    public String doAction(Game game) {
        System.out.println("####\n\n");
        System.out.println("New Game Screen:\n");
        boolean fg = true;
        int currScore = 0;
        String nextScreen = "GameEndScreen";
        while (fg){
            System.out.println("Current Score is : "+currScore+"\n");
            System.out.println("Enter any input:\n");
            String ip = new Scanner(System.in).next();
            if(ip.equals("pause")){
                nextScreen = "PauseScreen";
                break;
            }
            else if (!isNumeric(ip)){
                System.out.println("Your Final Score is : "+currScore);
                try{
                    System.out.println("Ending Game...");
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception e) {
                    System.out.println("Exception");
                }
                nextScreen = "GameEndScreen";
                break;
            }
            currScore += 1;
        }
        return nextScreen;

    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
