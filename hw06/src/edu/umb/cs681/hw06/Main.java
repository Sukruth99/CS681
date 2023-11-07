package edu.umb.cs681.hw06;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();

        AtomicLong dayTimeTrucksCount = new AtomicLong();
        AtomicLong trucksAtLocationCount = new AtomicLong();
        AtomicReference<String> mostFrequentLocation = new AtomicReference<>("");

        // Define the tasks as Runnable objects
        Runnable dayTimeTrucksTask = () -> dayTimeTrucksCount.set(processor.countDayTimeTrucks("Monday", "Lunch"));
        Runnable trucksAtLocationTask = () -> trucksAtLocationCount.set(processor.countTrucksAtLocation("Kenmore"));
        Runnable mostFrequentLocationTask = () -> mostFrequentLocation.set(processor.findMostFrequentLocation());

        // Start each task in a new thread
        Thread thread1 = new Thread(dayTimeTrucksTask);
        Thread thread2 = new Thread(trucksAtLocationTask);
        Thread thread3 = new Thread(mostFrequentLocationTask);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            // Wait for threads to finish
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the results
        System.out.println("Number of trucks on Monday at Lunch time: " + dayTimeTrucksCount.get());
        System.out.println("Total number of trucks at Kenmore: " + trucksAtLocationCount.get());
        System.out.println("Most Frequent Location: " + mostFrequentLocation.get());
    }
}
