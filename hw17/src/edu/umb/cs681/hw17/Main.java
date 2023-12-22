package edu.umb.cs681.hw17;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FoodTruckDataProcessor processor = new FoodTruckDataProcessor();
        List<List<String>> data = processor.getData();

        if (!data.isEmpty()) {
            // Passing day and time to DayTimeTrucksRunnable
            DayTimeTrucksRunnable dayTimeRunnable = new DayTimeTrucksRunnable(processor, data, "Monday", "Lunch");
            // Passing location to TrucksAtLocationRunnable
            TrucksAtLocationRunnable locationRunnable = new TrucksAtLocationRunnable(processor, data, "Kenmore");
            MostFrequentLocationRunnable frequentLocationRunnable = new MostFrequentLocationRunnable(processor, data);

            Thread t1 = new Thread(dayTimeRunnable);
            Thread t2 = new Thread(locationRunnable);
            Thread t3 = new Thread(frequentLocationRunnable);

            t1.start();
            t2.start();
            t3.start();

            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Number of Trucks on Monday at Lunch Time: " + dayTimeRunnable.getResult());
            System.out.println("Total Number of Trucks at Kenmore: " + locationRunnable.getResult());
            System.out.println("Most Frequent Location: " + frequentLocationRunnable.getResult());
        } else {
            System.out.println("No data was loaded.");
        }
    }
}
