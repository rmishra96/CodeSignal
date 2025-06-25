package com.codingsignaltest.cloudDBStorage;

import java.util.Map;
import java.util.stream.Collectors;

public class CloudStorage {
    private final Map<String,Integer> storage;
    public CloudStorage(){
        this.storage = new java.util.HashMap<>();
    }

    // ADD_FILE <name> <size>
    public boolean addFile(String name, int size){
        if(storage.containsKey(name)){
            return false;
        }
        storage.put(name,size);
        return true;

    }

    // GET_FILE_SIZE <name>
    public String getFileSize(String name){
        return storage.containsKey(name) ? String.valueOf(storage.get(name)) : "";
    }

    // DELETE_FILE <name>

    public String deleteFile(String name){
        return storage.containsKey(name) ? String.valueOf(storage.remove(name)) : "";
    }

    public String getNLargest(String prefix, int n){
        return storage.entrySet().stream().filter(entry -> entry.getKey().startsWith(prefix))
                .sorted( (e1,e2) -> {
                    int sizeComparison = Integer.compare(e2.getValue(),e1.getValue());
                    return sizeComparison != 0 ? sizeComparison : e1.getKey().compareTo(e2.getKey());
                }).limit(n).map(entry -> entry.getKey() + "(" + entry.getValue() + ")").collect(Collectors.toList()).toString();
    }

    public static void main(String[] args) {
        CloudStorage cloudStorage = new CloudStorage();
        System.out.println(cloudStorage.addFile("file1.txt", 100)); // true
        System.out.println(cloudStorage.addFile("file1.txt", 200)); // false
        System.out.println(cloudStorage.getFileSize("file1.txt")); // 100
        System.out.println(cloudStorage.getFileSize("file2.txt")); // ""
        System.out.println(cloudStorage.deleteFile("file1.txt")); // 100
        System.out.println(cloudStorage.deleteFile("file2.txt")); // ""



        CloudStorage cs = new CloudStorage();
        cs.addFile("alpha.txt", 500);
        cs.addFile("alpha1.txt", 1500);
        cs.addFile("alpha2.txt", 1500);
        cs.addFile("beta.txt", 1000);

        System.out.println(cs.getNLargest("alpha", 2));
        cloudStorage.getNLargest("file", 2); // Should return the two largest files with prefix "file"
        cloudStorage.getNLargest("file", 3); // Should return the two largest files with prefix "file"
        cloudStorage.getNLargest("file", 4); // Should return the two largest files with prefix "file"
        cloudStorage.getNLargest("file", 5); // Should return the two largest files with prefix "file"
    }
}
