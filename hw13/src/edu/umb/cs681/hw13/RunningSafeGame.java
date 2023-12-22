package edu.umb.cs681.hw13;

public class RunningSafeGame {
    public static void main(String[] args) {
        GameSafe game = new GameSafe();

        // Create threads simulating players
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            String playerName = "Player " + i;
            threads[i] = new Thread(() -> {
                System.out.println(playerName + " is trying to make a kill.");
                game.playerKillsEnemy(playerName);
            });
            threads[i].start();
        }

        // Join all threads
        for (Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Players who made the kill (Safe): " + game.getLastPlayersToKill());
    }
}

