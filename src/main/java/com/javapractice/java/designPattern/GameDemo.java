package com.javapractice.java.designPattern;

public class GameDemo {

    public static void main(String[] args) {
        Game game = new Game();
        game.registerState("MainMenuScreen", new MainMenuState());
        game.registerState("NewGameScreen", new NewGameState());
        game.registerState("StatsScreen", new StatsState());
        game.registerState("GameEndScreen", new GameEndState());
        game.registerState("PauseScreen", new PauseState());
        game.registerState("ClosingScreen", new ClosingScreenState());

        String nextScreen = game.execute();
        while (nextScreen != null){
            game.changeState(nextScreen);
            nextScreen = game.execute();
        }
    }
}
