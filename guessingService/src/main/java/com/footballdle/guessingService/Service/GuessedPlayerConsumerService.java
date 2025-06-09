package com.footballdle.guessingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.guessingService.Model.GuessResponse;
import com.footballdle.guessingService.Model.Player;

/*This consumed the information about the guessed player */
@Service
public class GuessedPlayerConsumerService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @KafkaListener(topics = "guessed-player-topic", groupId = "guessing-service-group")
public void listen(String message) {
    try {
        GuessResponse response = objectMapper.readValue(message, GuessResponse.class);

        String sessionId = response.getSessionId();
        Player player = response.getPlayer();

        System.out.println("Received guessed player for session " + sessionId + ": " + player.getPlayer());

    } catch (JsonProcessingException e) {
        System.err.println("Failed to deserialize GuessResponse: " + e.getMessage());
    }
}
}
