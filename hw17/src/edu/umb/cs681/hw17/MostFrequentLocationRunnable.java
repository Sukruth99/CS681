package edu.umb.cs681.hw17;
import java.util.List;

import java.util.concurrent.atomic.AtomicReference;

public class MostFrequentLocationRunnable implements Runnable {
    private FoodTruckDataProcessor processor;
    private List<List<String>> data;
    private AtomicReference<String> result = new AtomicReference<>();

    public MostFrequentLocationRunnable(FoodTruckDataProcessor processor, List<List<String>> data) {
        this.processor = processor;
        this.data = data;
    }

    public String getResult() {
        return result.get();
    }

    @Override
    public void run() {
        result.set(processor.getMostFrequentLocation(data));
    }
}