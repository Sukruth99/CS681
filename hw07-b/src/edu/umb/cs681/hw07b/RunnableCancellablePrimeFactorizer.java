package edu.umb.cs681.hw07b;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private volatile boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    private boolean isDone() {
        lock.lock();
        try {
            return done;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Stopped generating prime factors.");
                    break;
                }
                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                } else {
                    divisor = getNextDivisor(divisor);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private long getNextDivisor(long divisor) {
        return (divisor == 2) ? 3 : divisor + 2;
    }

    public void run() {
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer cancellableFactorizer =
                new RunnableCancellablePrimeFactorizer(65, 2, (long) Math.sqrt(65));
        Thread thread = new Thread(cancellableFactorizer);
        thread.start();

        try {
            Thread.sleep(1200);  // Delay for 1.2 seconds
            cancellableFactorizer.setDone();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Prime factors for the given range: " + cancellableFactorizer.getPrimeFactors());
    }
}
