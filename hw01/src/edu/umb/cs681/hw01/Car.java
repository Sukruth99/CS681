package edu.umb.cs681.hw01;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Car {
    private String make;
    private String model;
    private int mileage;
    private int year;
    private float price;
    private int dominationCount;

    public Car(String makeOfTheCar, String modelOfTheCar, int mileageOfCar, int yearOfCar, float priceOfCar) {
        this.make = makeOfTheCar;
        this.model = modelOfTheCar;
        this.mileage = mileageOfCar;
        this.year = yearOfCar;
        this.price = priceOfCar;
        this.dominationCount = 0;
    }

    public String getMake()
    {
        return make;
    }

    public int getMileage()
    {
        return mileage;
    }

    public float getPrice()
    {
        return price;
    }

    public int getDominationCount()
    {
        return dominationCount;
    }
    public void calculateDominationCount(List<Car> allCars) {
        this.dominationCount = 0;
        for (Car car : allCars) {
            if (this != car && this.dominates(car)) {
                this.dominationCount++;
            }
        }
    }
    public void setDominationCount(int x)
    {
        this.dominationCount = x;
    }

    public boolean dominates(Car car) {
        boolean hasBetterPrice = this.getPrice() <= car.getPrice();
        boolean hasBetterYear = this.getYear() >= car.getYear();
        boolean hasBetterMileage = this.getMileage() <= car.getMileage();

        return hasBetterPrice && hasBetterYear && hasBetterMileage &&
                (this.getPrice() < car.getPrice() || this.getYear() > car.getYear() || this.getMileage() < car.getMileage());
    }


    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public static void main(String[] args) {
        Car car1 = new Car("asdasdasd", "asdasdasdva", 454545, 6676, 40f);
    }
}