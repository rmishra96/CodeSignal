package com.leetcode.lockbasedThreading.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();

    public void producer(){
        try{
            lock.lock();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        }catch(Exception ex){

        }finally {
            lock.unlock();
            System.out.println("Lock release by: " + Thread.currentThread().getName());
        }
    }
}
