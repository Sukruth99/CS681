package edu.umb.cs681.hw08.fs;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
public class File extends FSElement {
    public File(Directory parent, String name, Integer size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    public boolean isDirectory() {
        return false;
    }
}
