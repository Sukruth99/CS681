package edu.umb.cs681.hw12;

public class WithdrawRunnable implements Runnable {
    private ThreadSafeBankAccount2 account;
    private volatile boolean done = false;
    private double amount = 350; // Default amount

    public WithdrawRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public void run() {
        while (!done) {
            account.withdraw(amount);
            pauseThread();
        }
    }

    private void pauseThread() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException exception) {
            return; // Exit the method if interrupted
        }
    }
}
