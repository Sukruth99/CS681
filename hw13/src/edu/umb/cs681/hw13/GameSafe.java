package edu.umb.cs681.hw13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class GameSafe {
    private List<String> lastPlayersToKill = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    public void playerKillsEnemy(String playerName) {
        lock.lock();
        try {
            lastPlayersToKill.add(playerName);
        } finally {
            lock.unlock();
        }
    }

    public List<String> getLastPlayersToKill() {
        lock.lock();
        try {
            return new ArrayList<>(lastPlayersToKill);
        } finally {
            lock.unlock();
        }
    }
}
