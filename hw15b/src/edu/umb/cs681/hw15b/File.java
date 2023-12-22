package edu.umb.cs681.hw15b;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentLinkedQueue;

public class File extends FSElement {
    protected ReentrantLock lock = new ReentrantLock();

    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    public void accept(ThreadLocal<FileCrawlingVisitor> visitorThreadLocal, AtomicBoolean done, ConcurrentLinkedQueue<File> sharedQueue) {
        lock.lock();
        try {
            if (done.get()) {
                return; // Stop if done is true
            }
            FileCrawlingVisitor v = visitorThreadLocal.get();
            v.visit(this);
        } finally {
            lock.unlock();
        }
    }
}
