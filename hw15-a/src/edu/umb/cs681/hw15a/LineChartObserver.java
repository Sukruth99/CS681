package edu.umb.cs681.hw15a;



public class LineChartObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.println("LineChartObserver - Ticker: " + event.ticker() + ", Quote: " + event.quote());
    }
}
