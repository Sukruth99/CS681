package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimes(){
        for (long candidate = from; candidate <= to; candidate++) {
            lock.lock();
            try {
                if(done) {
                    System.out.println("Stopped generating prime numbers.");
                    this.primes.clear();
                    break;
                }
                if(candidate > 2 && isEven(candidate)) {
                    continue; // Skip even numbers greater than 2
                }
                boolean isPrime = true;
                // Check for primality only up to the square root of the candidate number
                for(long divisor = 2; divisor <= Math.sqrt(candidate) && isPrime; divisor++) {
                    if(candidate % divisor == 0) {
                        isPrime = false;
                    }
                }
                if(isPrime) {
                    this.primes.add(candidate);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void run(){
        generatePrimes();
    }


}
