package edu.umb.cs681.hw08.fs;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
public class Link extends FSElement {
    private FSElement target;

    public Link(Directory parent, String name, LocalDateTime creationTime, FSElement target) {
        super(parent, name, 0, creationTime);
        this.target = target;
    }

    public boolean isDirectory() {
        return false;
    }

    public FSElement getTarget() {
        return target;
    }
}