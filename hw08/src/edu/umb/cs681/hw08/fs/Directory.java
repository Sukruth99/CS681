package edu.umb.cs681.hw08.fs;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
public class Directory extends FSElement {
    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
        this.children = new LinkedList<>();
    }

    public boolean isDirectory() {
        return true;
    }

    public LinkedList<FSElement> getChildren() {
        return children;
    }

    public void appendChild(FSElement child) {
        this.children.add(child);
    }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<>();
        for (FSElement e : children) {
            if (e.isDirectory()) {
                subDirectories.add((Directory) e);
            }
        }
        return subDirectories;
    }

    public LinkedList<File> getSubFiles() {
        LinkedList<File> subFiles = new LinkedList<>();
        for (FSElement e : children) {
            if (!e.isDirectory()) {
                subFiles.add((File) e);
            }
        }
        return subFiles;
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement e : children) {
            if (e.isDirectory()) {
                totalSize += ((Directory) e).getTotalSize();
            } else {
                totalSize += e.getSize();
            }
        }
        return totalSize;
    }
}

