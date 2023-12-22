package edu.umb.cs681.hw15a;

public class StockEvent {
    private final String ticker;
    private final double quote;


    public StockEvent(String ticker, double quote) {
        this.ticker = ticker;
        this.quote = quote;
    }

    public String getTicker() {
        return ticker;
    }

    public double getQuote() {
        return quote;
    }
}