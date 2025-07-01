package com.codingsignaltest.workinghours.level1;

import java.util.ArrayList;
import java.util.List;

public class Worker {
    String position;
    String compensation;
    List<Integer> timestamps = new ArrayList<>();
    boolean inOffice = false;

    Worker(String position, String compensation) {
        this.position = position;
        this.compensation = compensation;
    }

    void register(int timestamp) {
        timestamps.add(timestamp);
        inOffice = !inOffice;
    }

    int getTotaltime(){
        int total = 0;
        for(int i=0; i+1 < timestamps.size() ; i+=2)
            total += timestamps.get(i+1) - timestamps.get(i);
        return total;
    }
}
