package com.codingsignaltest.cloudDBStorage.level3;

import java.util.Map;

public class CloudStorage {
    private final Map<String,FileEntry> files;
    private final Map<String,User> users;

    public CloudStorage(){
        this.files = new java.util.HashMap<>();
        this.users = new java.util.HashMap<>();
        this.users.put("admin", new User("admin",Integer.MAX_VALUE)) ; // Admin has unlimited capacity
    }

    public boolean addUser(String userID,int capacity) {
        if(users.containsKey(userID)) {
            return false; // User already exists
        }
        users.put(userID, new User(userID, capacity));
        return true;
    }

    public boolean addFile(String name, int size){
        return addFileBy("admin", name, size) != " ";
    }

    public String addFileBy(String userId,String name,int size){
        if(files.containsKey(name) || !users.containsKey(userId)) {
            return " ";
        }

    }
}
