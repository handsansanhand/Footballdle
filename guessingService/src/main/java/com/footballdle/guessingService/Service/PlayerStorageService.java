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
        String fixedLeague = fixLeagueName(player);
        cachedPlayers.put(fixedLeague, player);
    }
    private String fixLeagueName(Player player) {
        String league = player.getLeague();
        switch (league) {
            case "Premier League":
                return "premier_league_players_table";
            case "La Liga":
                return "la_liga_players_table";
            case "Bundesliga":
                return "bundesliga_players_table";
            case "Serie A":
                return "serie_a_players_table";
            case "Ligue 1":
                return "ligue_1_players_table";
            case "Overall":
                return "overall_players_table";
            default:
                return league.toLowerCase().replace(" ", "_") + "_players_table"; 
                // fallback
    }
    }

    public Player getPlayerFromLeague(String league) {
        return cachedPlayers.get(league);
    }
    
    public Map<String, Player> getPlayers() {
        return cachedPlayers;
    }
}
