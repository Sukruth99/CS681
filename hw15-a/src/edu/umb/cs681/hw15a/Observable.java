package edu.umb.cs681.hw15a;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Observable<T> {
    private LinkedList<Observer<T>> observers = new LinkedList<>();
    private boolean changed = false;

    public void addObserver(Observer<T> o) {
        if (o == null) {
            throw new NullPointerException("Observer cannot be null");
        }
        synchronized (this) {
            if (!observers.contains(o)) {
                observers.add(o);
            }
        }
    }

    public void removeObserver(Observer<T> o) {
        synchronized (this) {
            observers.remove(o);
        }
    }

    protected void setChanged() {
        changed = true;
    }

    protected void clearChanged() {
        changed = false;
    }

    public boolean hasChanged() {
        synchronized (this) {
            return changed;
        }
    }

    public void notifyObservers(T event) {
        List<Observer<T>> observersLocal= null;

        synchronized (this) {
            if (!hasChanged()) {
                return;
            }
            observersLocal = new ArrayList<>(observers);
            clearChanged();
        }
        if (observersLocal != null) {
            for (Observer<T> observer : observersLocal) {
                observer.update(this, event);
            }
        }
    }
}
