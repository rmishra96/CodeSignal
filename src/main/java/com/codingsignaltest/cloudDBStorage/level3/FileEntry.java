package com.codingsignaltest.cloudDBStorage.level3;

public class FileEntry {
    String name;
    int size;
    String owner;

    public FileEntry(String name,int size, String owner){
        this.name = name;
        this.size = size ;
        this.owner = owner;
    }
}
