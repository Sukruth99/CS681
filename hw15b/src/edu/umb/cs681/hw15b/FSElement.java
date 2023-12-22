package edu.umb.cs681.hw15b;
import java.util.concurrent.ConcurrentLinkedQueue;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    protected String name;
    protected Directory parent;
    protected int size;
    protected LocalDateTime creationTime;
    protected ReentrantLock lock = new ReentrantLock();

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        this.name = name;
        this.parent = parent;
        this.size = size;
        this.creationTime = creationTime;
    }

    public abstract boolean isDirectory();

    public Directory getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public int getSize() {
        return size;
    }

    public abstract void accept(ThreadLocal<FileCrawlingVisitor> visitorThreadLocal, AtomicBoolean done, ConcurrentLinkedQueue<File> sharedQueue);
}

