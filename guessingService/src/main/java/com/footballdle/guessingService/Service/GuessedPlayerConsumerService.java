package com.footballdle.guessingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.guessingService.Model.Player;

/*This consumed the information about the guessed player */
@Service
public class GuessedPlayerConsumerService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "guessed-player-topic", groupId = "guessing-service-group") 
    public void listen(String message) {
        System.out.println("Heard something from guessed player");
         try {
            Player player = objectMapper.readValue(message, Player.class);
            System.out.println("Information about the guessed player: " + player.getPlayer());
    
         } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            System.err.println("Failed to deserialize player: " + e.getMessage());    
        }    
    }}
