package com.codingsignaltest.cloudDBStorage.level3;

public class User {
    String userId;
    int capacity;
    int used;

    public User(String userId,int capacity){
        this.userId = userId;
        this.capacity = capacity;
        this.used = 0;
    }

    public int remainingCapacity(){
        return capacity - used;
    }
    public void addUsage(int size) {
        used+= size;
    }

    public void reduceUsage(int size) {
        used -= size;
    }
}
