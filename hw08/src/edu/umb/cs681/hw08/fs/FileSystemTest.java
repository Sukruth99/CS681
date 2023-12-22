package edu.umb.cs681.hw08.fs;

import java.util.HashSet;
import java.util.Set;

public class FileSystemTest {
    public static void main(String[] args) {
        Set<FileSystem> fileSystems = new HashSet<>();
        Runnable getFsInstanceTask = () -> {
            FileSystem fs = FileSystem.getFileSystem();
            synchronized (fileSystems) {
                fileSystems.add(fs);
            }
            System.out.println("FileSystem instance: " + fs);
        };

        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(getFsInstanceTask);
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(" Total Number of unique FileSystem instances: " + fileSystems.size());
    }
}
