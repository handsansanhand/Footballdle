package com.footballdle.playerService.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.footballdle.playerService.Model.Player;
import com.footballdle.playerService.Repository.PlayerRepository;
import com.footballdle.playerService.Service.PlayerService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/players")
public class RandomPlayerController {

    @Autowired
    private PlayerService playerService;

    //this is called to refresh the current list of players
    @GetMapping("/refreshplayers")
    public Map<String, Player> refreshCurrentPlayers() {
        playerService.preLoadRandomPlayers();
        return getAllRandomPlayer();
    }
    
    @GetMapping("/random")
    public Map<String, Player> getAllRandomPlayer() {
        return playerService.getAllRandomCachedPlayers();
    }

    @GetMapping("/random/{league}")
    public Player getRandomPlayerFromLeague(@PathVariable String league) {
        String tableName = switch (league.toLowerCase()) {
            case "overall" -> "overall_players_table";
            case "premier" -> "premier_league_players_table";
            case "laliga" -> "la_liga_players_table";
            case "bundesliga" -> "bundesliga_players_table";
            case "ligue1" -> "ligue_1_players_table";
            case "seriea" -> "serie_a_players_table";
            default -> throw new IllegalArgumentException("Invalid league: " + league);
        };
        return playerService.getRandomPlayerFromTable(tableName);
    }

    @GetMapping("/names/{league}")
    public List<String> getPlayerNames(@PathVariable String league) {
        if(league.equals("Overall")) {
            return playerService.getAllPlayerNames();
        } else {
               return playerService.getAllPlayerNamesFromLeague(league);
        }
     
    }
    

}
