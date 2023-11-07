package edu.umb.cs681.hw06;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.IOException;


public class DataProcessor {
    private List<List<String>> data;

    public DataProcessor() {
        this.data = loadDataFromCSV();
    }

    private List<List<String>> loadDataFromCSV() {
        Path path = Paths.get("food_truck_schedule.csv");
        try (Stream<String> lines = Files.lines(path)) {
            return lines.skip(1)
                    .map(line -> Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public long countDayTimeTrucks(String day, String time) {
        return data.stream()
                .filter(record -> record.get(0).equalsIgnoreCase(day) && record.get(1).equalsIgnoreCase(time))
                .count();
    }

    public long countTrucksAtLocation(String location) {
        return data.stream()
                .filter(record -> record.get(3).equalsIgnoreCase(location))
                .count();
    }

    public String findMostFrequentLocation() {
        return data.stream()
                .collect(Collectors.groupingBy(record -> record.get(3), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No location data available");
    }

    // Getters to allow the Main class to access the data
    public List<List<String>> getData() {
        return data;
    }
}
