package edu.umb.cs681.hw10;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private LinkedList<FSElement> children = new LinkedList<>();

    public Directory(Directory parent, String name, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try {
            System.out.println("Getting children of directory: " + getName());
            return children;
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();
        try {
            System.out.println("Counting children of directory: " + getName());
            return children.size();
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            System.out.println("Getting subdirectories of directory: " + getName());
            LinkedList<Directory> dirList = new LinkedList<>();
            for(FSElement e : children) {
                if(e.isDirectory()) {
                    dirList.add((Directory)e);
                }
            }
            return dirList;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            System.out.println("Getting files of directory: " + getName());
            LinkedList<File> fileList = new LinkedList<>();
            for(FSElement e : children) {
                if(!e.isDirectory()) {
                    fileList.add((File)e);
                }
            }
            return fileList;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            System.out.println("Child appended: " + child.getName() + " to directory: " + getName());
            this.children.add(child);
        } finally {
            lock.unlock();
        }
    }

    public int getTotalSize() {
        lock.lock();
        try {
            System.out.println("Calculating total size of directory: " + getName());
            int totalSize = 0;
            for(FSElement e : children) {
                if(e instanceof Directory) {
                    totalSize += ((Directory)e).getTotalSize();
                } else {
                    totalSize += e.getSize();
                }
            }
            return totalSize;
        } finally {
            lock.unlock();
        }
    }
}

