package com.streampracticejava;

public class ThreadToPrintOddAndEven {

    int count  = 1;
    static int N;
    public void printOddNumber(){
        synchronized (this){
            while (count < N){
                while (count % 2 == 0){
                    try {
                        wait();
                    }catch (InterruptedException ex){
                        ex.getMessage();
                    }
                }
                System.out.println(count + " ");
                count++;
                notify();
            }
        }
    }

    public void printEvenNumber(){
        synchronized (this){
            while(count < 0){
                while(count % 2 ==1){
                    try {
                        wait();
                    }catch (InterruptedException ex){
                        ex.getMessage();
                    }
                    System.out.println(count +" ");
                    count++;
                    notify();

                }
            }
        }
    }

    public static void main(String[] args) {
        N = 10;
        ThreadToPrintOddAndEven threadToPrintOddAndEven = new ThreadToPrintOddAndEven();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadToPrintOddAndEven.printEvenNumber();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadToPrintOddAndEven.printOddNumber();
            }
        });

        t1.start();
        t2.start();

    }
}
