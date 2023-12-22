package edu.umb.cs681.hw12;

public class Main {
    public static void main(String[] args) {
        ThreadSafeBankAccount2 account = new ThreadSafeBankAccount2();
        DepositRunnable depositRunnable = new DepositRunnable(account);
        WithdrawRunnable withdrawRunnable = new WithdrawRunnable(account);

        // Set the deposit and withdraw amounts
        depositRunnable.setAmount(750);
        withdrawRunnable.setAmount(300);

        Thread[] depositThreads = new Thread[5];
        Thread[] withdrawThreads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            depositThreads[i] = new Thread(depositRunnable);
            withdrawThreads[i] = new Thread(withdrawRunnable);
            depositThreads[i].start();
            withdrawThreads[i].start();
        }

        try {
            Thread.sleep(8000); // Simulate time for operations
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            depositRunnable.setDone();
            withdrawRunnable.setDone();

            for (int i = 0; i < 5; i++) {
                depositThreads[i].interrupt();
                withdrawThreads[i].interrupt();
            }
        }

        for (int i = 0; i < 5; i++) {
            try {
                depositThreads[i].join();
                withdrawThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final balance is: " + account.getBalance());
    }
}
