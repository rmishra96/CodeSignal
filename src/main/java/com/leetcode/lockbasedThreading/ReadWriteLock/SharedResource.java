package com.leetcode.lockbasedThreading.ReadWriteLock;


import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {
    boolean isAvailable  = false;
    public void producer(ReadWriteLock lock){
        try {
            lock.readLock().lock();
            System.out.println("Read Lock acquired by:" + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(5000);

        }catch (Exception ex){}
        finally {
            lock.readLock().unlock();
            System.out.println("Write Lock release by :" + Thread.currentThread().getName());
        }
    }

    public void consume(ReadWriteLock lock){
        try {
            lock.writeLock().lock();
            System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
            isAvailable = false;
        }catch (Exception ex){}
        finally {
            lock.writeLock().unlock();
            System.out.println("Write lock release by : " + Thread.currentThread().getName());
        }
    }
}
