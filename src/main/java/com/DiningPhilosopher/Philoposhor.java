package com.DiningPhilosopher;

import java.util.Random;

public class Philoposhor implements Runnable{

    private int id;
    private volatile  boolean full;
    private Chopsticks leftChopStick;
    private Chopsticks rightChopStick;

    private Random random;

    private int eatingCounter;

    public Philoposhor(int id, Chopsticks leftChopStick, Chopsticks rightChopStick) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.random = new Random();
    }


    @Override
    public void run() {
        try {
            while(!full){
                think();
                if(leftChopStick.pickUp(this,State.LEFT)){
                    if(rightChopStick.pickUp(this,State.RIGHT)){
                        eat();
                        rightChopStick.putDown(this,State.RIGHT);
                    }
                    leftChopStick.putDown(this, State.LEFT);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void think() throws InterruptedException{
        System.out.println(this + " is thinking .. ");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException{
        System.out.println(this + " is eating ... ");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full)
    {
        this.full = full;
    }

    public boolean isFull(){
        return this.full;
    }

    @Override
    public String toString(){
        return "Philosphor " +id;
    }

    public int getEatingCounter() {
        return this.eatingCounter;
    }
}
