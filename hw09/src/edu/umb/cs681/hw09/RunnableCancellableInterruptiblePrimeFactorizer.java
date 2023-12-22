package edu.umb.cs681.hw09;



import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    // This method is used to interrupt the thread from the outside
    public void interrupt() {
        Thread.currentThread().interrupt();
    }

    @Override
    public void setDone() {
        super.setDone();
        interrupt();
    }

    // Check for prime numbers and add them to the factors list
    private void checkAndAddFactors(long divisor) {
        if (dividend % divisor == 0) {
            factors.add(divisor);
            dividend /= divisor;
        }
    }

    // Sleep method that allows the thread to be interruptible
    private void sleepInterruptibly() throws InterruptedException {
        Thread.sleep(1);
    }

    // Handle the interruption of the thread
    private void handleInterruption() {
        System.out.println("Thread interrupted");
        clearFactors();
    }

    // Clear the list of factors
    private void clearFactors() {
        lock.lock();
        try {
            factors.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to && !isDone()) {
            lock.lock();
            try {
                if (isEven(divisor) && divisor > 2) {
                    divisor++;
                    continue;
                }
                checkAndAddFactors(divisor);
                divisor = getNextDivisor(divisor);
            } finally {
                lock.unlock();
            }
            try {
                sleepInterruptibly();
            } catch (InterruptedException e) {
                handleInterruption();
                return;
            }
        }
    }

    @Override
    public void run() {
        generatePrimeFactors();
    }

    public static void main(String[] args) {
        RunnableCancellableInterruptiblePrimeFactorizer factorizer =
                new RunnableCancellableInterruptiblePrimeFactorizer(256, 2, (long)Math.sqrt(81));
        RunnableCancellableInterruptiblePrimeFactorizer factorizer2 =
                new RunnableCancellableInterruptiblePrimeFactorizer(256, 9, (long)Math.sqrt(7056));
        Thread thread = new Thread(factorizer);
        Thread thread2 = new Thread(factorizer);

        thread.start();
        thread2.start();


        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        factorizer.setDone();
        factorizer2.setDone();

        thread.interrupt();
        thread2.interrupt();
        System.out.println("thread1  result: " + factorizer.getPrimeFactors());
        System.out.println("thread2 result: " + factorizer2.getPrimeFactors());
    }
}
