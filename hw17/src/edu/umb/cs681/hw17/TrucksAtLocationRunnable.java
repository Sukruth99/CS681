package edu.umb.cs681.hw17;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TrucksAtLocationRunnable implements Runnable {
    private FoodTruckDataProcessor processor;
    private List<List<String>> data;
    private String location;
    private AtomicLong result = new AtomicLong();

    public TrucksAtLocationRunnable(FoodTruckDataProcessor processor, List<List<String>> data, String location) {
        this.processor = processor;
        this.data = data;
        this.location = location;
    }

    public long getResult() {
        return result.get();
    }

    @Override
    public void run() {
        result.set(processor.countTrucksAtLocation(data, location));
    }
}
