package edu.umb.cs681.hw03;

public class CarPriceResultHolder {
    private int numCarExamined = 0;
    private double totalSum = 0.0;

    public void includeCarPrice(float price) {
        numCarExamined++;
        totalSum += price;
    }

    public double getAverage() {
        return numCarExamined > 0 ? totalSum / numCarExamined : 0.0;
    }

    public void combine(CarPriceResultHolder other) {
        totalSum += other.totalSum;
        numCarExamined += other.numCarExamined;
    }
}
