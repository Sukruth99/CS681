package edu.umb.cs681.hw12;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();
    private AtomicBoolean done = new AtomicBoolean(false);

    public void deposit(double amount){
        lock.lock();
        try {
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() + " (d): current balance: " + balance);

            while (!done.get() && balance >= 350) {
                System.out.println(Thread.currentThread().getId() + " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }

            if (done.get()) {
                System.out.println("Thread " + Thread.currentThread().getId() + " is terminating as per request.");
                return; // Exit if done is true
            }

            balance += amount;
            System.out.println(Thread.currentThread().getId() + " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        } catch (InterruptedException exception) {
            System.out.println("Thread " + Thread.currentThread().getId() + " interrupted during deposit.");
            exception.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }



    public void withdraw(double amount) {
        lock.lock();
        try {
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() + " (w): current balance: " + balance);

            while (!done.get() && balance <= 0) {
                System.out.println(Thread.currentThread().getId() + " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }

            if (done.get()) {
                System.out.println("Thread " + Thread.currentThread().getId() + " is terminating as per request.");
                return; // Exit method if termination is requested
            }

            balance -= amount;
            System.out.println(Thread.currentThread().getId() + " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }
    public void setDone() {
        done.set(true);
    }

    public boolean isDone() {
        return done.get();
    }

    public double getBalance() { return this.balance; }

    public static void main(String[] args){
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        for(int i = 0; i < 5; i++){
            new Thread( new DepositRunnable(bankAccount) ).start();
            new Thread( new WithdrawRunnable(bankAccount) ).start();
        }
    }
}
