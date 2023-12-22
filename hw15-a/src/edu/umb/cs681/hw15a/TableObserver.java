package edu.umb.cs681.hw15a;

public class TableObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.println("TableObserver - Ticker: " + event.ticker() + ", Quote: " + event.quote());
    }
}

