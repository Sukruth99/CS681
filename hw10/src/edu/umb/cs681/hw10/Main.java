package edu.umb.cs681.hw10;



import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private AtomicBoolean done = new AtomicBoolean(false);

    public void simulateFileOperations() {
        FileSystem fs = FileSystem.getFileSystem();
        Directory rootElement=new Directory(null,"root",LocalDateTime.now());
        fs.appendRootDir(rootElement);
//        if (!fs.getRootDirs().isEmpty()) {
            Directory root = fs.getRootDirs().getFirst(); // Assuming there's at least one root directory
//        }
//        else {
//            System.out.println("No root directories available.");
//            return;
//        }
        File file = new File(rootElement, "file" + Thread.currentThread().getId(), 100, LocalDateTime.now());

        while (!done.get()) {
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
            // Perform file system operations

            System.out.println("Thread " + Thread.currentThread().getId() +
                    " added a file. Total size: " + root.getTotalSize());
            // Wait to simulate file operations
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread " + Thread.currentThread().getId() + " interrupted");
                return; // Stop the operation if the thread is interrupted
            }
        }

        System.out.println("Thread " + Thread.currentThread().getId() + " is done");
    }


    public void setDone() {
        done.set(true);
    }

    public static void main(String[] args) {
        Main system = new Main();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> system.simulateFileOperations());
            threads[i].start();
        }

        try {
            // Simulate a condition that will end the file system operation
            Thread.sleep(1000); // Or some other logic to set 'done'
            system.setDone();
            for (Thread t : threads) {
                t.interrupt(); // Two-step thread termination
            }
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
