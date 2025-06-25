package com.codingsignaltest;

import java.util.ArrayList;
import java.util.List;

public class FileNode {
    String name;
    boolean isDirectory;
    int size;
    List<FileNode> children;

   public FileNode(String name,boolean isDirectory, int size){
       this.name = name;
       this.isDirectory = isDirectory;
       this.size = size;
       if(isDirectory){
           this.children = new ArrayList<>();
       }
   }

   public void addChild(FileNode child) {
       if (this.isDirectory) {
           this.children.add(child);
       } else {
           throw new UnsupportedOperationException("Cannot add child to a file node");
       }
   }

   public int getTotalSize(){
       if (! isDirectory) return size;

       int total = 0;
       for (FileNode child : children) {
           total += child.getTotalSize();
       }
       return total;
   }
   public void printStructure(String indent) {
       System.out.println(indent + (isDirectory ? "+- " : "- ") + name + (isDirectory ? "" : " " + size + " Bytes"));
         if (isDirectory) {
              for (FileNode child : children) {
                child.printStructure(indent + "  ");
              }
         }
   }
}
