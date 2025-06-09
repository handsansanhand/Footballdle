package com.footballdle.guessingService.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.footballdle.guessingService.Model.Player;
import com.footballdle.guessingService.Service.GuessPublishService;
import com.footballdle.guessingService.Service.PlayerStorageService;

@RestController
public class GuessController {
   
    private final PlayerStorageService players;
    private final GuessPublishService guess;
    @Autowired
    public GuessController(com.footballdle.guessingService.Service.PlayerStorageService players, GuessPublishService guess) {
        this.players = players;
        this.guess = guess; 
    }
    
    @GetMapping("/players")
    public Map<String, Player> getAllPlayers() {
        return players.getPlayers();
    }

    @PostMapping("/players/guess")
    public void retrievePlayer(@RequestBody GuessRequest request) {
    guess.sendGuessRequest(request.getPlayerName());
}
}
