package com.parabola.peak;

public class ThreadOddEven {
    int i = 1;

    public synchronized  void printOddNumber(String name) throws InterruptedException {
        if(name.equals("Thread1")){
            if(i%2 == 1){
                System.out.println(name + " " + i++);
                notify();
            }else{
                wait(10);
            }

        }else if(name.equals("Thread2")){
            if(i%2 == 0){
                System.out.println(name +" " +i++);
                notify();
            }else{
                wait(10);
            }
        }
    }

    public static void main(String[] args) {
        ThreadOddEven threadOddEven = new ThreadOddEven();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(threadOddEven.i <= 10){
                    try {
                        threadOddEven.printOddNumber(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(threadOddEven.i <= 10){
                    try {
                        threadOddEven.printOddNumber(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t1.setName("Thread1");
        t2.setName("Thread2");
        t1.start();
        t2.start();
    }
}
