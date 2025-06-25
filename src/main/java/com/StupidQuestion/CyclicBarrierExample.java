package com.StupidQuestion;

import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        ExecutorService service =  Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All tasks have been finished");
            }
        });
        for(int i=0; i < 5; ++i){
            service.execute(new Barrier(i+1,barrier));
        }
    }
}

class Barrier implements  Runnable{

    private int id;
    private Random random;
    private CyclicBarrier barrier;

    public Barrier(int id, CyclicBarrier barrier){
        this.id = id;
        this.random = new Random();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID " +this.id + " starts the work .....");
        try{
            Thread.sleep(random.nextInt(3000));
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        System.out.println("After the await()...");
    }
}
