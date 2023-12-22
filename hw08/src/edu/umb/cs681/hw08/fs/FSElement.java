    package edu.umb.cs681.hw08.fs;


    import java.time.LocalDateTime;
    import java.util.LinkedList;
    import java.time.LocalDateTime;
    import java.util.LinkedList;
    import java.util.concurrent.locks.ReentrantLock;

    public abstract class FSElement {
        protected String name;
        protected Directory parent;
        protected Integer size;
        protected LocalDateTime creationTime;

        public FSElement(Directory parent, String name, Integer size, LocalDateTime creationTime) {
            this.name = name;
            this.parent = parent;
            this.creationTime = creationTime;
            this.size = size;
        }

        public abstract boolean isDirectory();

        public Directory getParent() {
            return parent;
        }

        public LocalDateTime getCreationTime() {
            return creationTime;
        }

        public String getName() {
            return name;
        }

        public Integer getSize() {
            return size;
        }
    }