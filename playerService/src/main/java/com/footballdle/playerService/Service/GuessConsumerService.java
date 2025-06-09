package com.footballdle.playerService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.playerService.Model.Player;

/*Listens to the guess (a string), looks up the player, then returns the full player */
@Service
public class GuessConsumerService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PlayerService playerService;
    @KafkaListener(topics = "guess-topic", groupId = "player-service-group") 
    public void listen(String message) {
         try {
            Player player = playerService.getPlayerFromTable(message, message);
            System.err.println("Found a player: " + player);  
         } catch (Exception e) {
            // TODO Auto-generated catch block
            System.err.println("Error: " + e.getMessage());    
        }    
    }


}
