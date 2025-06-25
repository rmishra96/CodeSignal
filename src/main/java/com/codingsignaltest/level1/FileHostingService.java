package com.codingsignaltest.level1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileHostingService {
    private static class File {
        private String name;
        private int size;

        public File(String name, int size) {
            this.name = name;
            this.size = size;
        }
    }
    private final Map<String,File> files = new HashMap<>();

    // upload file
    public void FILE_UPLOAD(String fileName, int fileSize){
        if(files.containsKey(fileName)) {
            throw new IllegalArgumentException("File already exists");
        }
        files.put(fileName, new File(fileName, fileSize));
    }

    // Get file
    public Integer FILE_GET(String fileName){
        File file = files.get(fileName);
        return file != null ? file.size : null;
    }

    // Copy file
    public void FILE_COPY(String sourceFileName, String destinationFileName) {
        if (!files.containsKey(sourceFileName)) {
            throw new IllegalArgumentException("Source file does not exist");
        }
        if (files.containsKey(destinationFileName)) {
            throw new IllegalArgumentException("Destination file already exists");
        }
        File sourceFile = files.get(sourceFileName);
        files.put(destinationFileName, new File(destinationFileName, sourceFile.size));
    }

    // Search file by prefix
    public List<String> FILE_SEARCH(String prefix) {
            return files.values().stream().filter(f -> f.name.startsWith(prefix))
                    .sorted(Comparator.comparingInt((File f) -> f.size).reversed()
                            .thenComparing(f -> f.name)).limit(10).map(f -> f.name).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        

         FileHostingService service = new FileHostingService();

         service.FILE_UPLOAD("file-1.zip", 4321);
         service.FILE_UPLOAD("file-2.txt", 1100);
         service.FILE_UPLOAD("file-3.csv", 2122);
         service.FILE_UPLOAD("file-4.mdx", 3378);

         System.out.println("Size of file-2.txt: " + service.FILE_GET("file-2.txt"));

         service.FILE_COPY("file-2.txt", "file-2-copy.txt");
         System.out.println("Size of file-2-copy.txt: " + service.FILE_GET("file-2-copy.txt"));

         System.out.println("Search results for 'file': " + service.FILE_SEARCH("file"));

    }
}
