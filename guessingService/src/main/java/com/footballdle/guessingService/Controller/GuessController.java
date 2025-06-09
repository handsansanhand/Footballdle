package com.footballdle.guessingService.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.footballdle.guessingService.Model.Player;
import com.footballdle.guessingService.Service.PlayerStorageService;

@RestController
public class GuessController {
   
    private final PlayerStorageService players;
    @Autowired
    public GuessController(com.footballdle.guessingService.Service.PlayerStorageService players) {
        this.players = players;
    }
    
    @GetMapping("/players")
    public Map<String, Player> getAllPlayers() {
        return players.getPlayers();
    }
}
