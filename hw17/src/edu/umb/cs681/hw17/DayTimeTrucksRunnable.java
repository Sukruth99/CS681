package edu.umb.cs681.hw17;

import java.util.concurrent.atomic.AtomicLong;
import java.util.List;

public class DayTimeTrucksRunnable implements Runnable {
    private FoodTruckDataProcessor processor;
    private List<List<String>> data;
    private String day;
    private String time;
    private AtomicLong result = new AtomicLong();
    public long getResult() {
        return result.get();
    }

    public DayTimeTrucksRunnable(FoodTruckDataProcessor processor, List<List<String>> data, String day, String time) {
        this.processor = processor;
        this.data = data;
        this.day = day;
        this.time = time;
    }

    @Override
    public void run() {
        long count = processor.countDayTimeTrucks(data, day, time);
        System.out.println("Number of trucks on " + day + " at " + time + " time: " + count);
    }
}