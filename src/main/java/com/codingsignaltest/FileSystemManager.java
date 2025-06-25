package com.codingsignaltest;

import java.io.File;
import java.util.Optional;

public class FileSystemManager {

    private final FileNode root;
    private final int sizeLimit;

    public FileSystemManager(FileNode root, int sizeLimit) {
        this.root = root;
        this.sizeLimit = sizeLimit;
    }

    public boolean addFile(String[] path, String fileName, int size){
        FileNode current = root;
        for (String dir : path) {
            Optional<FileNode> next = current.children.stream().filter(child -> child.isDirectory && child.name.equals(dir)).findFirst();
            if(next.isPresent()) {
                current = next.get();
            } else {
                FileNode newDir = new FileNode(dir, true, 0);
                current.addChild(newDir);
                current = newDir;
            }
        }
        if(root.getTotalSize() + size > sizeLimit) {
            System.out.println("Cannot add file " + fileName + ": Size limit exceeded.");
            return false;
        }
        current.addChild(new FileNode(fileName, false, size));
        return true;
    }

    public void printFileSystem(){
        root.printStructure("");
        System.out.println("Total size: " + root.getTotalSize() + " Bytes / " +sizeLimit + " Bytes");
    }

    public static void main(String[] args) {
        FileNode root = new FileNode("root", true, 0);
        FileSystemManager fsm = new FileSystemManager(root, 1000);

        fsm.addFile(new String[]{"dir1"}, "file1.txt", 200);
        fsm.addFile(new String[]{"dir1"}, "file2.txt", 300);
        fsm.addFile(new String[]{"dir2"}, "file3.txt", 400);
        fsm.addFile(new String[]{"dir2", "subdir1"}, "file4.txt", 100);

        fsm.printFileSystem();

        // Attempt to add a file that exceeds the size limit
        fsm.addFile(new String[]{"dir1"}, "largefile.txt", 600);
    }
}
