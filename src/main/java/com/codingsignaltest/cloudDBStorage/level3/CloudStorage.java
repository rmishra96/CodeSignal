package com.codingsignaltest.cloudDBStorage.level3;

import java.util.*;

public class CloudStorage {
    Map<String,File> storage = new HashMap<>();
    Map<String,User> users = new HashMap<>();
    static final String TRUE = "true";
    static final String FASE = "false";

    public CloudStorage(){
        users.put("admin", new User("admin",0));
    }

    public String addFile(String name,String sizeStr){
        if(storage.containsKey(name)) return FASE;
        int size = Integer.parseInt(sizeStr);
        File f = new File(name,size,"admin");
        storage.put(name,f);
        users.get("admin").files.add(f);
        return TRUE;
    }

    public String addUser(String userId,String capacityStr){
        if(users.containsKey(userId)) return FASE;
        int capacity = Integer.parseInt(capacityStr);
        users.put(userId, new User(userId,capacity));
        return TRUE;
    }

    public String mergeUser(String userId1,String userId2){
        if(!users.containsKey(userId1) || !users.containsKey(userId2) || userId1.equals(userId2)){
            return "";
        }
        User u1 = users.get(userId1);
        User u2 = users.get(userId2);

        u1.files.addAll(u2.files);
        u1.capacity += u2.capacity;
        users.remove(userId2);
        return String.valueOf(u1.capacity);
    }

    public String addFileBy(String userId,String name,String sizeStr){
        if (storage.containsKey(name)) return "";
        User user = users.get(userId);
        int size = Integer.parseInt(sizeStr);
        if (user.capacity < size) return "";
        File f = new File(name, size, userId);
        user.capacity -= size;
        user.files.add(f);
        storage.put(name, f);
        return String.valueOf(user.capacity);
    }

    public String getFileSize(String name) {
        if (!storage.containsKey(name)) return "";
        return String.valueOf(storage.get(name).size);
    }

    public String deleteFile(String name) {
        if (!storage.containsKey(name)) return "";
        File f = storage.get(name);
        User u = users.get(f.userId);
        u.capacity += f.size;
        u.files.remove(f);
        storage.remove(name);
        return String.valueOf(f.size);
    }

    public String nLargest(String prefix, String nStr) {
        int n = Integer.parseInt(nStr);
        List<File> filtered = new ArrayList<>();
        for (File f : storage.values()) {
            if (f.name.startsWith(prefix)) {
                filtered.add(f);
            }
        }
        if (filtered.isEmpty()) return "";
        filtered.sort(Comparator.comparingInt((File f) -> -f.size).thenComparing(f -> f.name));
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(n, filtered.size()); i++) {
            File f = filtered.get(i);
            result.add(f.name + "(" + f.size + ")");
        }
        return String.join(", ", result);
    }

    public String backup(String userId) {
        if (!users.containsKey(userId)) return "";
        User user = users.get(userId);
        user.backup = new HashSet<>(user.files);
        return String.valueOf(user.files.size());
    }

    public String restore(String userId) {
        if (!users.containsKey(userId)) return "";
        User user = users.get(userId);
        for (File f : new HashSet<>(user.files)) {
            deleteFile(f.name);
        }
        if (user.backup == null) return "0";
        int count = 0;
        for (File f : user.backup) {
            if (!storage.containsKey(f.name)) {
                addFileBy(userId, f.name, String.valueOf(f.size));
                count++;
            }
        }
        return String.valueOf(count);
    }
}
