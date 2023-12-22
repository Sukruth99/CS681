package edu.umb.cs681.hw11;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private LinkedList<Directory> rootDirs = new LinkedList<>();
    private static AtomicReference<FileSystem> instance = new AtomicReference<>();
    private ReentrantLock lock = new ReentrantLock();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        instance.compareAndSet(null, new FileSystem());
        return instance.get();
    }

    public void appendRootDir(Directory root) {
        lock.lock();
        try {
            rootDirs.add(root);
            System.out.println("Root dir is being  added: " + root.getName());
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs() {
        lock.lock();
        try {
            System.out.println("Fetching list of root dirs.");
            return rootDirs;
        } finally {
            lock.unlock();
        }
    }

    public int countRootDir() {
        lock.lock();
        try {
            System.out.println("Counting root dirs.");
            return rootDirs.size();
        } finally {
            lock.unlock();
        }
    }

    public void clearRootDir() {
        lock.lock();
        try {
            rootDirs.clear();
            System.out.println("All root directories have been cleared.");
        } finally {
            lock.unlock();
        }
    }
}


