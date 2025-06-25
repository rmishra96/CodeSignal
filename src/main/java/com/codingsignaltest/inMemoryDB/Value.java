package com.codingsignaltest.inMemoryDB;

public class Value {

    int value;
    Integer ttl; // milliseconds
    long lastSetAt;

    public Value(int value, long lastSetAt, Integer ttl) {
        this.value = value;
        this.lastSetAt = lastSetAt;
        this.ttl = ttl;
    }

    boolean isExpired(long currentTimestamp) {
        return ttl != null && (ttl + lastSetAt) <= currentTimestamp;
    }
}
