package com.codingsignaltest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FilehostingService {
    private static class FileData {
        String name;
        byte[] content;

        FileData(String name, byte[] content) {
            this.name = name;
            this.content = content;
        }
    }

    private final Map<Integer,FileData> storage = new HashMap<>();
    private final AtomicInteger fileIdcounter = new AtomicInteger(1);

    // upload file
    public int uploadFile(String name, byte[] content) {
        int fileId = fileIdcounter.getAndIncrement();
        storage.put(fileId, new FileData(name, content));
        return fileId;
    }

    // Download file
    public byte[] downloadFile(int id){
        FileData fileData = storage.get(id);
        if (fileData != null) {
            return fileData.content;
        } else {
            throw new IllegalArgumentException("File with ID " + id + " does not exist.");
        }
    }

    // Return list of files
    public List<String> listFiles() {
        return storage.values().stream()
                .map(fileData -> fileData.name)
                .toList();
    }

    // Delete a file
    public boolean deleteFile(int id){
        return storage.remove(id) != null;
    }

    public static void main(String[] args) {
        FilehostingService service = new FilehostingService();
        int fileId = service.uploadFile("example.txt", "Hello, World!".getBytes());
        System.out.println("Uploaded file with ID: " + fileId);

        byte[] content = service.downloadFile(fileId);
        System.out.println("Downloaded content: " + new String(content));

        System.out.println("List of files: " + service.listFiles());

        boolean deleted = service.deleteFile(fileId);
        System.out.println("File deleted: " + deleted);
    }
 }
