package com.codingsignaltest.cloudDBStorage.level3;

import java.util.HashSet;
import java.util.Set;

public class User {
    String userId;
    int capacity;
    Set<File> files;
    Set<File> backup;

    public User(String userId, int capacity) {
        this.userId = userId;
        this.capacity = capacity;
        this.files = new HashSet<>();
        this.backup = null;
    }
}
