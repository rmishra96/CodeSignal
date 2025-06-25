package com.DiningPhilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philoposhor[] philoposhors = null;
        try {
            philoposhors = new Philoposhor[Constants.NUMBER_OF_PHILOPSHER];
            Chopsticks[] chopsticks = new Chopsticks[Constants.NUMBER_OF_CHOPSTICKS];

            for(int i =0 ; i < Constants.NUMBER_OF_CHOPSTICKS; ++i)
                chopsticks[i] = new Chopsticks(i);

             executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOPSHER);
                for(int i=0; i < Constants.NUMBER_OF_PHILOPSHER ; ++i){
                    philoposhors[i] = new Philoposhor(i,chopsticks[i],chopsticks[(i+1)%Constants.NUMBER_OF_PHILOPSHER]);
                    executorService.execute(philoposhors[i]);
                }

                Thread.sleep(Constants.NUMBER_OF_CONSTANTS);
                executorService.shutdown();

                for(Philoposhor  philoposhor : philoposhors){
                    philoposhor.setFull(true);
                }

        }finally {
            executorService.shutdown();
            while(!executorService.isTerminated())
                Thread.sleep(1000);
            for(Philoposhor philoposhor: philoposhors)
                System.out.println(philoposhor+ " eat #" + philoposhor.getEatingCounter() + " times ");
        }
    }
}
