package com.leetcode.lockbasedThreading.StampedLock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    boolean isAvailable = false;
    StampedLock lock = new StampedLock();

    public void producer(){
        long stamped = lock.readLock();
        try{
            System.out.println("Read lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(6000);
        }catch (Exception ex){

        }finally {
            lock.unlockRead(stamped);
            System.out.println("Read lock release by: " + Thread.currentThread().getName());
        }
    }


    public void consume(){
        long stamp = lock.writeLock();
        try{
            System.out.println("Write lock acquired by: " +Thread.currentThread().getName());
            isAvailable = false;

        }catch (Exception ex){

        }finally {
            lock.unlockWrite(stamp);
            System.out.println("Write lock release by: " +Thread.currentThread().getName());
        }
    }
}
