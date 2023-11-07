package edu.umb.cs681.hw04;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public interface DistanceMetric {
    double distance(List<Double> p1, List<Double> p2);
}
