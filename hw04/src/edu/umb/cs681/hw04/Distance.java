package edu.umb.cs681.hw04;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Distance {
    public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
        return metric.distance(p1, p2);
    }

    // Overloaded method using Euclidean distance by default.
    public static double get(List<Double> p1, List<Double> p2)

    {
        return get(p1, p2, new Euclidean());
    }

    // Generates a distance matrix for a list of points using the provided DistanceMetric
    public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
        return points.stream()
                .map(p1 -> points.stream()
                        .map(p2 -> get(p1, p2, metric))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    // Overloaded method to use Euclidean distance by default as mentioned in CS680
    public static List<List<Double>> matrix(List<List<Double>> points)
    {
        return matrix(points, new Euclidean());
    }
}
