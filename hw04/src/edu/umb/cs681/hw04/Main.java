package edu.umb.cs681.hw04;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Generate 1,000 points, each with 100 dimensions
        List<List<Double>> points = generateRandomPoints(1000, 100);

        // Calculate distance matrices for each DistanceMetric
        List<List<Double>> euclideanMatrix = Distance.matrix(points, new Euclidean());
        List<List<Double>> manhattanMatrix = Distance.matrix(points, new Manhattan());
        List<List<Double>> chebyshevMatrix = Distance.matrix(points, new Chebyshev());

        // Print the distance matrices
        System.out.println("Euclidean Distance Matrix:");
        printMatrix(euclideanMatrix);

        System.out.println("\nManhattan Distance Matrix:");
        printMatrix(manhattanMatrix);

        System.out.println("\nChebyshev Distance Matrix:");
        printMatrix(chebyshevMatrix);
    }

    private static void printMatrix(List<List<Double>> matrix) {
        matrix.forEach(row -> {
            row.forEach(value -> System.out.printf("%.2f ", value));
            System.out.println();
        });
    }

    private static List<List<Double>> generateRandomPoints(int count, int dimensions) {
        Random rand = new Random();
        return IntStream.range(0, count)
                .mapToObj(i -> rand.doubles(dimensions, 0, 10).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
/* i have implemented  code with more than 1,000 points. Having 100+
dimensions for each point , this will result in a very large amount
 of output since we're printing out the entire distance matrix for
  1,000 points in 100 dimensions. The matrices will be 1,000 by 1,000 elements in size,
  which is quite large, it is taking roughly 3 minutes to print all the distances

 */