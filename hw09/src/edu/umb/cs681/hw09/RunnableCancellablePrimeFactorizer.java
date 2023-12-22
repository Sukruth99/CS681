package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    protected  volatile boolean done = false;
    protected  final ReentrantLock lock = new ReentrantLock();

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

    protected boolean isDone() {
        lock.lock();
        try {
            return done;
        } finally {
            lock.unlock();
        }
    }

    private void updateFactors(long divisor) {
        factors.add(divisor);
        dividend /= divisor;
    }

    private void incrementDivisor(long divisor) {
        if(divisor == 2) {
            divisor++;
        } else {
            divisor += 2;
        }
    }

    private boolean shouldContinue(long divisor) {
        return dividend != 1 && divisor <= to && !isDone();
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (shouldContinue(divisor)) {
            lock.lock();
            try {
                if(isEven(divisor) && divisor > 2) {
                    divisor = getNextOddDivisor(divisor);
                    continue;
                }
                if(dividend % divisor == 0) {
                    updateFactors(divisor);
                } else {
                    divisor = getNextDivisor(divisor);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    protected long getNextOddDivisor(long divisor) {
        return divisor + 1;
    }

    protected long getNextDivisor(long divisor) {
        return (divisor == 2) ? divisor + 1 : divisor + 2;
    }

    public void run() {
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer cancellableFactorizer =
                new RunnableCancellablePrimeFactorizer(36, 2, (long) Math.sqrt(36));
        Thread thread = new Thread(cancellableFactorizer);
        thread.start();
        cancellableFactorizer.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + cancellableFactorizer.getPrimeFactors());
    }
}