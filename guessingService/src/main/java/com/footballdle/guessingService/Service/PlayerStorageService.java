package com.footballdle.guessingService.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.footballdle.guessingService.Model.Player;

/*Class responsible for storing the players locally in cache */
@Service
public class PlayerStorageService {
    private Map<String, Player> cachedPlayers = new HashMap<>();

    public void storePlayer(String league, Player player) {
        cachedPlayers.put(league, player);
    }
    
    public Map<String, Player> getPlayers() {
        return cachedPlayers;
    }
}
