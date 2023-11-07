package edu.umb.cs681.hw04;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Chebyshev implements DistanceMetric  {
    @Override
    public double distance(List<Double> p1, List<Double> p2) {
        // Ensure that both points have the same number of dimensions
        if (p1.size() != p2.size()) {
            throw new IllegalArgumentException("Points must have the same number of dimensions");
        }

        // Calculate the Chebyshev distance
        double maxDiff = 0;
        for (int i = 0; i < p1.size(); i++) {
            double diff = Math.abs(p1.get(i) - p2.get(i));
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        return maxDiff;
    }
}
