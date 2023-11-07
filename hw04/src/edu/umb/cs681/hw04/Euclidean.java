package edu.umb.cs681.hw04;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Euclidean implements DistanceMetric {

    public double distance(List<Double> p1, List<Double> p2) {
        return Math.sqrt(
                IntStream.range(0, p1.size())
                        .mapToDouble(i -> Math.pow(p1.get(i) - p2.get(i), 2))
                        .sum()
        );
    }
}
