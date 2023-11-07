package edu.umb.cs681.hw05;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FoodTruckDataProcessor processor = new FoodTruckDataProcessor();
        List<List<String>> data = processor.getData();

        if (!data.isEmpty()) {
//            System.out.println("Number of Trucks on Monday at Lunch Time:");
            processor.printDayTimeTrucks(data);

//            System.out.println("\nTotal Number of Trucks at Kenmore:");
            processor.printTrucksAtLocation(data);

//            System.out.println("\nMost Frequent Location:");
            processor.printMostFrequentLocation(data);
        } else {
            System.out.println("No data was loaded.");
        }
    }
}
