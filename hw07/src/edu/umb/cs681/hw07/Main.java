package edu.umb.cs681.hw07;

public class Main {
    public static void main(String[] args) {
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1, 100);
        Thread t = new Thread(gen);
        t.start();
        gen.setDone(); // Call this method to stop prime number generation
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + gen.getPrimes().size() + " prime numbers were found.");
    }
}
