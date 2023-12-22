package edu.umb.cs681.hw17;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodTruckDataProcessor {
    private List<List<String>> loadDataFromCSV() {
        Path path = Paths.get("food_truck_schedule.csv");
        try (Stream<String> lines = Files.lines(path)) {
            return lines.skip(1) // Skip header line
                    .map(line -> Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public long countDayTimeTrucks(List<List<String>> data, String day, String time) {
        System.out.println("Using parallel streams in Trucksatlocationrunnable class");
        return data.stream()
                .parallel()
                .filter(record -> record.get(0).equalsIgnoreCase(day) && record.get(1).equalsIgnoreCase(time))
                .count(); // Just count the entries since 'Trucks' column does not contain numbers
    }

    public String getMostFrequentLocation(List<List<String>> data) {
        System.out.println("Using parallel streams");
        return data.stream()
                .parallel()
                .collect(Collectors.groupingBy(record -> record.get(3), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No location data available");
    }


    public long countTrucksAtLocation(List<List<String>> data, String location) {
        System.out.println("Using parallel streams");
        return data.stream()
                .parallel()
                .filter(record -> record.get(3).equalsIgnoreCase(location))
                .count(); // Again, just count the entries
    }

    public void printTrucksAtLocation(List<List<String>> data) {
        long count = countTrucksAtLocation(data, "Kenmore");
        System.out.println("Total number of trucks at Kenmore: " + count);
    }





    public void printMostFrequentLocation(List<List<String>> data) {
        String mostFrequentLocation = data.stream()
                .collect(Collectors.groupingBy(record -> record.get(3), Collectors.counting()))
                .entrySet()
                .stream()
                .parallel()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No location data available");

        System.out.println("Most Frequent Location: " + mostFrequentLocation);
    }

    // You can access this method from the Main class to get the loaded data
    public List<List<String>> getData()
    {
        return loadDataFromCSV();
    }
}