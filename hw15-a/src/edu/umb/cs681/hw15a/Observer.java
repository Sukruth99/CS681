package edu.umb.cs681.hw15a;

public interface Observer<T> {
    void update(Observable<T> sender, T event);
}
