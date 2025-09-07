package com.javapractice.java.designPattern.state;

public class LaunchScreenState implements State {

    @Override
    public String doAction(Game game) {
        System.out.println("#####\n\n");
        System.out.println("LaunchingScreen:");
        System.out.println("Welcome!!");
        System.out.println("\n\n#####");
        return "MainMenuScreen";
    }
}
