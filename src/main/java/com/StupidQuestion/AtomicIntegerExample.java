package com.StupidQuestion;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    private static AtomicInteger counter = new AtomicInteger(0);
    public static void increment() {
        for(int i=0;i<10000;i++)
            counter.getAndIncrement();
    }

    public static void main(String[] args) {
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("Counter: "+counter);
    }
}
