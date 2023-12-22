package edu.umb.cs681.hw13;



import java.util.ArrayList;
import java.util.List;

public class GameUnsafe {
    private List<String> lastPlayersToKill = new ArrayList<>();

    public void playerKillsEnemy(String playerName) {
        lastPlayersToKill.add(playerName);
    }

    public List<String> getLastPlayersToKill() {
        return new ArrayList<>(lastPlayersToKill);
    }
}
