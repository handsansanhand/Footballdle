package com.footballdle.guessingService.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.guessingService.Model.Player;
@Service
public class OverallPlayerConsumerService {
    @Autowired
    private final PlayerStorageService playerStorageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

     public OverallPlayerConsumerService(PlayerStorageService playerStorageService) {
        this.playerStorageService = playerStorageService;
    }

    @KafkaListener(topics = "overall-players-topic", groupId = "guessing-service-group") 
    public void listen(String message) {
         try {
            Player player = objectMapper.readValue(message, Player.class);
            System.out.println("OVERALL Stored player: " + player.getPlayer() + " for league " + player.getLeague());
            playerStorageService.storePlayer("Overall", player);
         } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            System.err.println("Failed to deserialize player: " + e.getMessage());    
        }    
    }
}

