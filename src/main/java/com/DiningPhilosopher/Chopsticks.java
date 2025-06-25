package com.DiningPhilosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopsticks {
    private int id;
    private Lock lock;

    public Chopsticks(int id){
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philoposhor philoposhor,State state){
        try {
           if( lock.tryLock(10, TimeUnit.MILLISECONDS) ){
               System.out.println(philoposhor + " pick up " + state.toString() + " " + this);
               return true;
           }
           return false;


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void putDown(Philoposhor philoposhor,State state){
        lock.unlock();
        System.out.println(philoposhor+ " puts down " +state.toString()+" " + this);
    }

    @Override
    public String toString(){
        return "ChopSticks " +id;
    }





}
