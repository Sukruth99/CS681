package edu.umb.cs681.hw08.fs;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
public class FileSystem {
    private LinkedList<Directory> rootDirs;
    private static FileSystem fs;
    private static final ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
        rootDirs = new LinkedList<>();
    }

    public static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (fs == null) {
                fs = new FileSystem();
            }
            return fs;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }

    public void appendRootDir(Directory root) {
        this.rootDirs.add(root);
    }
}
