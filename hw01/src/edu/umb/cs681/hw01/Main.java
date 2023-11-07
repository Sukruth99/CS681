package edu.umb.cs681.hw01;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import static java.util.stream.Collectors.partitioningBy;

public class Main {
    private ArrayList<Car> cars = new ArrayList<>();

    public Main() {
        // Initialize the list of cars in the constructor
        Car car1 = new Car("A", "A", 57000, 2000, 10000);
        Car car2 = new Car("B", "B", 23000, 2001, 9000);
        Car car3 = new Car("C", "C", 19000, 2002, 8000);
        Car car4 = new Car("D", "D", 34000, 2003, 7000);
        Car car5 = new Car("E", "E", 69000, 2004, 6000);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        for (Car car : cars) {
            car.calculateDominationCount(cars);
        }
    }

    public void priceComparator() {
        // Separate the cars into high and low groups
        List<Car> highPriceCars = separateCarsByHighPriceThreshold(cars, 7000);
        List<Car> lowPriceCars = separateCarsByLowPriceThreshold(cars, 7000);

        // Sort the cars in each group and print a summary
        printCarGroupSummary(highPriceCars, "Price");
        printCarGroupSummary(lowPriceCars, "Price");
    }

    public void mileageComparator() {
        // Separate the cars into high and low groups based on mileage threshold
        List<Car> highMileageCars = separateCarsByHighMileageThreshold(cars, 50000);
        List<Car> lowMileageCars = separateCarsByLowMileageThreshold(cars, 50000);

        // Sort the cars in each group and print a summary
        printCarGroupSummary(highMileageCars, "Mileage");
        printCarGroupSummary(lowMileageCars, "Mileage");
    }

    public void yearComparator() {
        // Separate the cars into high and low groups based on year threshold
        List<Car> highYearCars = separateCarsByHighYearThreshold(cars, 2002);
        List<Car> lowYearCars = separateCarsByLowYearThreshold(cars, 2002);

        // Sort the cars in each group and print a summary
        printCarGroupSummary(highYearCars, "Year");
        printCarGroupSummary(lowYearCars, "Year");
    }

    public void dominationCountComparator() {
        // Separate the cars into high and low groups based on domination count threshold
        List<Car> highDominationCountCars = separateCarsByHighDominationCountThreshold(cars, 2);
        List<Car> lowDominationCountCars = separateCarsByLowDominationCountThreshold(cars, 2);

        // Sort the cars in each group and print a summary
        printCarGroupSummary(highDominationCountCars, "Domination Count");
        printCarGroupSummary(lowDominationCountCars, "Domination Count");
    }


    // Separation methods for mileage, year, and domination count
    private static List<Car> separateCarsByHighPriceThreshold(List<Car> cars, float threshold) {
        return cars.stream().filter(car -> car.getPrice() >= threshold).collect(Collectors.toList());
    }

    private static List<Car> separateCarsByLowPriceThreshold(List<Car> cars, float threshold) {
        return cars.stream().filter(car -> car.getPrice() < threshold).collect(Collectors.toList());
    }

    private static List<Car> separateCarsByHighMileageThreshold(List<Car> cars, int threshold) {
        return cars.stream().filter(car -> car.getMileage() >= threshold).collect(Collectors.toList());
    }

    private static List<Car> separateCarsByLowMileageThreshold(List<Car> cars, int threshold) {
        return cars.stream().filter(car -> car.getMileage() < threshold).collect(Collectors.toList());
    }

    private static List<Car> separateCarsByHighYearThreshold(List<Car> cars, int threshold) {
        return cars.stream().filter(car -> car.getYear() >= threshold).collect(Collectors.toList());
    }

    private static List<Car> separateCarsByLowYearThreshold(List<Car> cars, int threshold) {
        return cars.stream().filter(car -> car.getYear() < threshold).collect(Collectors.toList());
    }

    private static List<Car> separateCarsByHighDominationCountThreshold(List<Car> cars, int threshold) {
        return cars.stream().filter(car -> car.getDominationCount() >= threshold).collect(Collectors.toList());
    }

    private static List<Car> separateCarsByLowDominationCountThreshold(List<Car> cars, int threshold) {
        return cars.stream().filter(car -> car.getDominationCount() < threshold).collect(Collectors.toList());
    }

    private static void printCarGroupSummary(List<Car> cars, String category) {
        System.out.println("Sorting by " + category);

        if (!cars.isEmpty()) {
            // Calculate the average, highest, and lowest values
            Double averageValue = 0.0;
            Double highestValue = 0.0;
            Double lowestValue = 0.0;

            if ("Price".equals(category)) {
                averageValue = cars.stream().mapToDouble(car -> car.getPrice()).average().orElse(0.0);
                highestValue = cars.stream().mapToDouble(car -> car.getPrice()).max().orElse(0.0);
                lowestValue = cars.stream().mapToDouble(car -> car.getPrice()).min().orElse(0.0);
            } else if ("Mileage".equals(category)) {
                averageValue = cars.stream().mapToDouble(car -> car.getMileage()).average().orElse(0.0);
                highestValue = cars.stream().mapToDouble(car -> car.getMileage()).max().orElse(0.0);
                lowestValue = cars.stream().mapToDouble(car -> car.getMileage()).min().orElse(0.0);
            } else if ("Year".equals(category)) {
                averageValue = cars.stream().mapToDouble(car -> car.getYear()).average().orElse(0.0);
                highestValue = cars.stream().mapToDouble(car -> car.getYear()).max().orElse(0.0);
                lowestValue = cars.stream().mapToDouble(car -> car.getYear()).min().orElse(0.0);
            } else if ("Domination Count".equals(category)) {
                averageValue = cars.stream().mapToDouble(car -> car.getDominationCount()).average().orElse(0.0);
                highestValue = cars.stream().mapToDouble(car -> car.getDominationCount()).max().orElse(0.0);
                lowestValue = cars.stream().mapToDouble(car -> car.getDominationCount()).min().orElse(0.0);
            }

            System.out.println("Group: " + cars);
            System.out.println("Average value: " + averageValue);
            System.out.println("Highest value: " + highestValue);
            System. out.println("Lowest value: " + lowestValue);
        } else {
            System.out.println("No cars in this category.");
        }

        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("for each category 2 sets of values will be printed the first one is for high");
        System.out.println("and the second set is for low based on their corresponding threshold");
        // Sort and print cars by price
        System.out.println("Sorting by Price:");
        main.priceComparator();
        System.out.println();

        // Sort and print cars by mileage
        System.out.println("Sorting by Mileage:");
        main.mileageComparator();
        System.out.println();

        // Sort and print cars by year
        System.out.println("Sorting by Year:");
        main.yearComparator();
        System.out.println();

        // Sort and print cars by domination count
        System.out.println("Sorting by Domination Count:");
        main.dominationCountComparator();
    }
}

