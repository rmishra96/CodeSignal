package com.parabola.peak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;



public class PeakElement  {

    // 10 thread single by single

    // {4, 4, 5, 2, 5, 5, 1, 1, 1, 1, 1}


//    public static void main(String[] args) {
//        List<Integer> li  = Arrays.asList(4, 4, 5, 2, 5, 5, 1, 1, 1, 1, 1);
//        for( Integer i : li){
//            int x = ;
//        }
//    }


        static class MyRunnable implements  Runnable {

            private static final int LIMIT = 20;
            private static volatile  int counter =0;

            private int id;
            public MyRunnable(int id){
                this.id = id;
            }

            @Override
            public  void run() {
                outer:
                while(counter < LIMIT){
                    int NB_THREADS = 10;
                    while(counter % NB_THREADS !=id){
                        if(counter == LIMIT) break outer;
                    }
                    System.out.println("Thread "+Thread.currentThread().getName()+ " printed " + counter);
                    counter += 1;
                }
            }
        }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable(1));
t1.start(); }

}
