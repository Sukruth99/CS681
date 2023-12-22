package edu.umb.cs681.hw16;

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
        quotes.put(t, q);
        setChanged();
        if (hasChanged()) {
            clearChanged();
            notifyObservers(new StockEvent(t, q));
        }
    }

    protected void setChanged() {
        changed = true;
    }

    public void clearChanged() {
        changed = false;
    }

    public boolean hasChanged() {
        return changed;
    }

    public HashMap<String, Double> getQuotes() {
        return new HashMap<>(quotes);
    }
}