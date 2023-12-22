package edu.umb.cs681.hw11;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {
    private FSElement target;
    protected ReentrantLock lock = new ReentrantLock();

    public Link(Directory parent, String name, LocalDateTime creationTime, FSElement target) {
        super(parent, name, 0, creationTime);
        this.target = target;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    public void accept(ThreadLocal<FileCrawlingVisitor> visitorThreadLocal, AtomicBoolean done, LinkedList<File> sharedList) {
        lock.lock();
        try {
            if (done.get()) {
                return; // Stop if done is true
            }
            FileCrawlingVisitor v = visitorThreadLocal.get();
            v.visit(this);
            if (!done.get()) {
                target.accept(visitorThreadLocal, done, sharedList);
            }
        } finally {
            lock.unlock();
        }
    }

    public FSElement getTarget() {
        return target;
    }
}
