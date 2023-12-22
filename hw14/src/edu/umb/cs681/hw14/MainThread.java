package edu.umb.cs681.hw14;

import java.util.stream.IntStream;

public class MainThread {
    public static void main(String[] args) {
        StockQuoteObservable stockQuoteObservable = new StockQuoteObservable();

        // Register observers
        stockQuoteObservable.addObserver(new TableObserver());
        stockQuoteObservable.addObserver(new LineChartObserver());
        stockQuoteObservable.addObserver(new ThreeDObserver());

        // Create threads for each observer type
        Thread[] threads = new Thread[23];
        IntStream.rangeClosed(0, 22).forEach(i -> {
            threads[i] = new Thread(() -> {
                try {
                    double quote = Math.random() * 4070 + 1550; // Generate random stock quote
                    System.out.println("New stock quote generated: " + quote);
                    stockQuoteObservable.changeQuote("Boogie", quote);
                    Thread.sleep(100); // Simulating some delay
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted.");
                } finally {
                    // Any cleanup code if necessary
                    System.out.println("Thread " + Thread.currentThread().getId() + " is terminating.");
                }
            });
            threads[i].start();
        });

        // Wait for threads to finish
        try {
            Thread.sleep(100000); // Wait for a period to allow all threads to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            for (Thread thread : threads) {
                thread.interrupt();
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.out.println("MainThread interrupted during join.");
                }
            }
            System.out.println("MainThread exiting.");
        }

        // Output final stock quotes
        System.out.println("Updated stock quotes: " + stockQuoteObservable.getQuotes());
    }
}
