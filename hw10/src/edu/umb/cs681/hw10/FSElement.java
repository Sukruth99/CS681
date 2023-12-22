package edu.umb.cs681.hw10;




import java.time.LocalDateTime;
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
        if (parent!=null){
            parent.appendChild(this);
        }
    }

    public abstract boolean isDirectory();

    public Directory getParent() {
        lock.lock();
        try{
            return parent;
        }
        finally {
            lock.unlock();
        }


    }

    public LocalDateTime getCreationTime() {
        lock.lock();
        try{
            return creationTime;
        }
       finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try{
            return name;
        }
        finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try{
            return size;
        }
        finally {
            lock.unlock();
        }
    }
}
