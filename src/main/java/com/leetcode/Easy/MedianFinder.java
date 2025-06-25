package com.leetcode.Easy;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> pq1 ;
    PriorityQueue<Integer> pq2 ;

    public MedianFinder(){
        pq1 = new PriorityQueue<>(Collections.reverseOrder());
        pq2 = new PriorityQueue<>();
    }

    void add(int val){
        if(pq1.size() ==0 && pq2.size() == 0){
            pq1.add(val);
        }else {
            if(val < pq1.peek()){
                pq1.add(val);
            }else {
                pq2.add(val);
            }

        }
        int diff = Math.abs(pq1.size() - pq2.size());
        if(diff >1){
            if(pq1.size() > pq2.size()){
                pq2.add(pq1.remove());
            }else {
                pq1.add(pq2.remove());
            }
        }
    }

    int findMedian(){

        if(pq1.size() >= pq2.size()){
            return pq1.peek();
        }else {
            return pq2.peek();
        }

    }

    int remove(){
        if(pq1.size() >= pq2.size()){
            return pq1.remove();
        }else {
            return pq2.remove();
        }
    }
}
