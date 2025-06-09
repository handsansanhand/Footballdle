package com.footballdle.playerService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.playerService.Model.GuessRequest;
import com.footballdle.playerService.Model.GuessResponse;
import com.footballdle.playerService.Model.Player;

/*Listens to the guess (a string), looks up the player, then returns the full player */
@Service
public class GuessConsumerService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GuessedPlayerPublisherService guessedPlayerPublisherService;



    @KafkaListener(topics = "guess-topic", groupId = "player-service-group")
    public void listen(String message) {
    try {
        GuessRequest guessRequest = objectMapper.readValue(message, GuessRequest.class);

        String playerName = guessRequest.getPlayerName();
        String sessionId = guessRequest.getSessionId();

        Player player = playerService.getPlayerFromTable("overall_players_table", playerName);
        System.err.println("Found a player: " + player);

        // create response with player + sessionId
        GuessResponse response = new GuessResponse();
        response.setSessionId(sessionId);
        response.setPlayer(player);

        guessedPlayerPublisherService.publishGuessedPlayer(response);

    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
}


}
