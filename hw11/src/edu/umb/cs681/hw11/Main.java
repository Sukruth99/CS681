package edu.umb.cs681.hw11;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private AtomicBoolean done = new AtomicBoolean(false);
    private LinkedList<File> sharedList = new LinkedList<>();
    private LinkedList<Thread> threads = new LinkedList<>();

    public static void main(String[] args) {
        Main system = new Main();

        // Create file system and directory trees
        FileSystem fs = FileSystem.getFileSystem();
        Directory root1 = system.createDirectoryTree("DriveRoot1", 1);
        Directory root2 = system.createDirectoryTree("DriveRoot2", 2);
        Directory root3 = system.createDirectoryTree("DriveRoot3", 3);
        Directory root4 = system.createDirectoryTree("DriveRoot4", 4);
        Directory root5 = system.createDirectoryTree("DriveRoot5", 5);
        Directory[] dirs = {root1, root2, root3, root4, root5};

        // Create and start threads for each directory tree
        for (Directory dir : dirs) {
            ThreadLocal<FileCrawlingVisitor> visitorThreadLocal = ThreadLocal.withInitial(FileCrawlingVisitor::new);
            Thread thread = new Thread(() -> {
                System.out.println("Thread " + Thread.currentThread().getId() + " started crawling.");
                dir.accept(visitorThreadLocal, system.done, system.sharedList);

                // After crawling, add identified files to the shared list
                synchronized (system.sharedList) {
                    visitorThreadLocal.get().getFiles().forEach(file -> {
                        system.sharedList.add(file);
                        System.out.println("Thread " + Thread.currentThread().getId() +
                                " added file: " + file.getName() + " to shared list");
                    });
                }
                visitorThreadLocal.remove();
                System.out.println("Thread " + Thread.currentThread().getId() + " finished crawling.");
            });
            system.threads.add(thread);
            thread.start();
        }

        // Simulate a condition that will end the file system operation
        try {
            Thread.sleep(10000); // Let's say we wait for 10 seconds to simulate some operations
            System.out.println("Main thread signaling crawling threads to stop...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Signal all crawling threads to stop
        system.setDone();

        // Wait for all threads to finish
        system.threads.forEach(t -> {
            try {
                t.join();
                System.out.println("Thread " + t.getId() + " has been terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Collect identified files from the shared list
        System.out.println("Main thread collecting identified files from the shared list:");
        synchronized (system.sharedList) {
            system.sharedList.forEach(file -> System.out.println("Main thread collected file: " + file.getName()));
        }

        System.out.println("File system operations completed.");
    }

    private void setDone() {
        done.set(true);
        threads.forEach(Thread::interrupt);
        System.out.println("Main thread set 'done' flag and interrupted crawling threads.");
    }

    private Directory createDirectoryTree(String name, int treeNum) {
        Directory root = new Directory(null, name, LocalDateTime.now());

        // Manually adding files to the root directory
        root.appendChild(new File(root, "File" + treeNum + "_1", 150, LocalDateTime.now()));
        root.appendChild(new File(root, "File" + treeNum + "_2", 250, LocalDateTime.now()));
        root.appendChild(new File(root, "File" + treeNum + "_3", 400, LocalDateTime.now()));

        // Manually adding subdirectories and their files
        for (int i = 1; i <= 3; i++) {
            Directory subDir = new Directory(root, "SubDir" + treeNum + "_" + i, LocalDateTime.now());
            root.appendChild(subDir);
            subDir.appendChild(new File(subDir, "SubFile" + treeNum + "_" + i + "_1", 111, LocalDateTime.now()));
            subDir.appendChild(new File(subDir, "SubFile" + treeNum + "_" + i + "_2", 228, LocalDateTime.now()));
        }

        return root;
    }
}
