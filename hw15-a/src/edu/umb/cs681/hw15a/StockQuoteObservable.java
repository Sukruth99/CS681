package edu.umb.cs681.hw15a;

import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;

public class StockQuoteObservable extends Observable<StockEvent> {
    private HashMap<String, Double> quotes = new HashMap<>();
    private ReentrantLock lockQuotes = new ReentrantLock();
    private boolean changed = false;

    public void changeQuote(String t, double q) {
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
        changed = true;
    }

    public void clearChanged() {
        changed = false;
    }

    public boolean hasChanged() {
        return changed;
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

