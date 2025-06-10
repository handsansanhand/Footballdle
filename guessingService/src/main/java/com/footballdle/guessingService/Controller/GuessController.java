package com.footballdle.guessingService.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.footballdle.guessingService.Model.GuessRequest;
import com.footballdle.guessingService.Model.GuessResponse;
import com.footballdle.guessingService.Model.Player;
import com.footballdle.guessingService.Service.GuessPublishService;
import com.footballdle.guessingService.Service.GuessResponseManager;
import com.footballdle.guessingService.Service.PlayerStorageService;

@RestController
public class GuessController {
   
    private final PlayerStorageService players;
    private final GuessPublishService guess;
    private final GuessResponseManager guessResponseManager;
    @Autowired
    public GuessController(com.footballdle.guessingService.Service.PlayerStorageService players, GuessPublishService guess, GuessResponseManager guessResponseManager) {
        this.players = players;
        this.guess = guess; 
        this.guessResponseManager = guessResponseManager;
    }
    
    @GetMapping("/players")
    public Map<String, Player> getAllPlayers() {
        return players.getPlayers();
    }

    @PostMapping("/players/guess")
    public Map<String, Object> retrievePlayer(@RequestBody GuessRequest request) throws Exception {
        System.out.println("Recieved initial guess request " + request);
        System.out.println("For player " + request.getPlayerName());
        System.out.println("And league " + request.getLeague());
        System.out.println();
        CompletableFuture<GuessResponse> future = guessResponseManager.registerSession(request.getSessionId());
    guess.sendGuessRequest(request); //publish to Kafka
    try {
        GuessResponse guessedPlayerResponse = future.get(5, TimeUnit.SECONDS);
        Player correctPlayer = players.getPlayerFromLeague(request.getLeague());
        guessedPlayerResponse.setCorrectPlayer(correctPlayer);
        System.out.println("Comparing the two now...");
        Map<String, Object> result = GuessHandler.generateAnswerFromGuess(guessedPlayerResponse);
        return result;

    } catch (Exception e) {
        e.printStackTrace();
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Could not retrieve player " + request.getPlayerName());
        return errorResponse;
    }
    }

}
