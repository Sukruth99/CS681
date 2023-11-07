package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Camry", 50000, 2019, 20000f));
        cars.add(new Car("Honda", "Accord", 30000, 2018, 18000f));
        cars.add(new Car("Ford", "Focus", 40000, 2017, 12000f));

        CarPriceResultHolder carPriceResultHolder = cars.stream()
                .collect(CarPriceResultHolder::new,
                        (result, car) -> result.includeCarPrice(car.getPrice()),
                        (result1, result2) -> result1.combine(result2));

        System.out.println("The average car price is: $" + carPriceResultHolder.getAverage());
    }
}
