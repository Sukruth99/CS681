package edu.umb.cs681.hw16;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public abstract class Observable<T> {
    private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();

    public void addObserver(Observer<T> o) {
        observers.add(o);
    }

    public void removeObserver(Observer<T> o) {
        observers.remove(o);
    }

    public int countObservers() {
        return observers.size();
    }

    public void clearObservers() {
        observers.clear();
    }
/*
i have implemented forEach method from ConcurrentLinkedQueue to iterate over each observer
 in the collection and call their update method with the event. This replaces the
  manual iteration with a for-loop that was previously used when LinkedList and ReentrantLock were in place.

 */
    public void notifyObservers(T event) {
        observers.forEach(new Consumer<Observer<T>>() {
            public void accept(Observer<T> observer) {
                observer.update(Observable.this, event);
            }
        });
    }
}

