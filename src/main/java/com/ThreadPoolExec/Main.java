package com.ThreadPoolExec;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ThreadPoolExecutor executor  = new ThreadPoolExecutor(2,4,
                10, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2),
                new CustomThreadFactory(), new CustomRejectHandler());
        executor.allowCoreThreadTimeOut(true);

        for(int i=0; i <25;i++){
            executor.submit(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("Thread name :" +Thread.currentThread().getName());
                }catch (Exception ex){

                }
            });
        }
        executor.shutdown();
    }


}

class CustomRejectHandler implements RejectedExecutionHandler{


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task Denied: " + r.toString());
    }
}

class CustomThreadFactory implements  ThreadFactory
{

    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);

        return th;
    }
}