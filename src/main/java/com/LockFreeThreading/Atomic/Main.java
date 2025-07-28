package com.LockFreeThreading.Atomic;

public class Main {

    public static void main(String[] args) {
        SharedResource resource  = new SharedResource();

        Thread t1 = new Thread(() -> {
            for(int i=0; i<= 200 ; i++){
                resource.increament();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=0; i <= 200 ;i++){
                resource.increament();
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (Exception ex)
        {

        }

        System.out.println(resource.get());
    }
}
