package com.LockFreeThreading.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {

    AtomicInteger counter = new AtomicInteger(0);

    public void increament(){
        counter.incrementAndGet();
    }

    public int get(){
        return counter.get();
    }

}
