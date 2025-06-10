package com.footballdle.playerService.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footballdle.playerService.Model.Player;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;

@Service
public class PlayerService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PlayerPublisherService playerPublisher;
    @Autowired
    private OverallPlayerPublisherService overallPlayerPublisher;
    //at the start of the program, cache some players
    private Map<String, Player> cachedPlayers = new HashMap<>();
    
        private final List<String> tableNames = List.of(
        "overall_players_table",
        "premier_league_players_table",
        "la_liga_players_table",
        "bundesliga_players_table",
        "ligue_1_players_table",
        "serie_a_players_table"
    );

    @PostConstruct
    public void preLoadRandomPlayers() {
        for(String table : tableNames) {
            Player player = getRandomPlayerFromTable(table);
            cachedPlayers.put(table, player);
            System.out.println("Publishing player: " + player.getPlayer() + " from table: " + table);
            if(table.equals("overall_players_table")) {
                overallPlayerPublisher.publishPlayer(player);
            }
            else {
                playerPublisher.publishPlayer(player);   
            }
          
        }
    }

    //make the sql request
    public Player getRandomPlayerFromTable(String tableName) {
        String sql = "SELECT * FROM " + tableName + " ORDER BY RANDOM() LIMIT 1";
        return (Player) entityManager.createNativeQuery(sql, Player.class).getSingleResult();
    }

    public Player getPlayerFromTable(String tableName, String playerName) {
        String sql = "SELECT * FROM overall_players_table WHERE player = :playerName";
            return (Player) entityManager.createNativeQuery(sql, Player.class)
                    .setParameter("playerName", playerName)
                    .getSingleResult();
    }

    public Map<String, Player> getAllRandomCachedPlayers() {
        return cachedPlayers;
    }

    public Player getCachedPlayer(String tableName) {
        return cachedPlayers.get(tableName);
    }
}
