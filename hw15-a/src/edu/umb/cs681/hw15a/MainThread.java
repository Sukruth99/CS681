package edu.umb.cs681.hw15a;

import java.util.stream.IntStream;

public class MainThread {
    public static void main(String[] args) {
        StockQuoteObservable stockQuoteObservable = new StockQuoteObservable();

        // Register observers
        stockQuoteObservable.addObserver(new TableObserver());
        stockQuoteObservable.addObserver(new LineChartObserver());
        stockQuoteObservable.addObserver(new ThreeDObserver());

        // Create and start threads for generating stock quotes
        Thread[] threads = new Thread[10];
        IntStream.rangeClosed(0, 9).forEach(i -> {
            threads[i] = new Thread(() -> {
                double quote = Math.random() * 5000 + 1000; // Generate random stock quote
                String ticker = "Bogie";
                System.out.println("Thread " + Thread.currentThread().getId() + " - New stock quote for " + ticker + ": " + quote);
                stockQuoteObservable.changeQuote(ticker, quote);
            });
            threads[i].start();
        });

        // Join all threads
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread " + t.getId() + " interrupted.");
            }
        }

        // Final output of stock quotes
        System.out.println("Final updated stock quotes: " + stockQuoteObservable.getQuotes());
    }
}
/*
Each thread prints out the stock quote it generates along with its thread ID.
  understanding of how multiple threads are interacting
  with the StockQuoteObservable object. After all threads have finished, the final
  stock quotes are printed out, showing the result of all the updates.

This approach will demonstrate the thread-safe behavior of your implementation and
how the open call in notifyObservers() works.
 */