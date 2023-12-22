package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;

public class StockQuoteObservable extends Observable<StockEvent> {
    private HashMap<String, Double> quotes = new HashMap<>();   
    private ReentrantLock lockQuotes = new ReentrantLock();
    private boolean changed = false;
    /*here according  to the lecture notes I am assuming the 2 shared variables as quotes and changed
    or if its not changed then you might be talking about the shared variable as observers (Linked List)
    so i am considering both and using 2 reentrant Locks to guard them!
     */

    public void changeQuote(String t, double q) {
        //protecting the shared variable
        lockQuotes.lock();
        try {
            quotes.put(t, q);
            setChanged();
        } finally {
            lockQuotes.unlock();
        }
        notifyObservers(new StockEvent(t, q));
    }

    protected void setChanged() {

        lockQuotes.lock();
        try{
        changed = true;
    }
        finally {
            lockQuotes.unlock();
        }
        }

    public void clearChanged() {

        lockQuotes.lock();
        try{
            changed = false;
        }
        finally {
            lockQuotes.unlock();
        }
    }

    public boolean hasChanged() {

        lockQuotes.lock();
        try{
           return changed;
        }
        finally {
            lockQuotes.unlock();
        }
    }

    public void notifyObservers(StockEvent event) {
        if (hasChanged()) {
            clearChanged(); // Reset the change flag after notifying observers
            super.notifyObservers(event);
        }
    }

    public HashMap<String, Double> getQuotes() {
        return new HashMap<>(quotes);
    }
}
