package com.javapractice.java.designPattern.state;

import java.util.HashMap;

public class Game {

    private State currState;
    private HashMap<String, State> states = new HashMap<>();

    Game(){
        this.registerState("LaunchingScreen", new LaunchScreenState());
        this.currState = this.states.get("LaunchingScreen");
    }

    public void changeState(String stateDesc){
        this.currState = this.states.get(stateDesc);
    }

    public void registerState(String key, State state){
        this.states.put(key, state);
    }

    public String execute(){
        return this.currState.doAction(this);
    }
}
