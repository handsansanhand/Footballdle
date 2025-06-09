package com.footballdle.guessingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.guessingService.Model.Player;

/*This class should connect to the kafka container, subscribe to the queue, and maybe store the answer in cache */
@Service
public class PlayerConsumerService {
    @Autowired
    private final PlayerStorageService playerStorageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

     public PlayerConsumerService(PlayerStorageService playerStorageService) {
        this.playerStorageService = playerStorageService;
    }

    @KafkaListener(topics = "players-topic", groupId = "guessing-service-group") 
    public void listen(String message) {
         try {
            Player player = objectMapper.readValue(message, Player.class);
            System.out.println("Stored player: " + player.getPlayer());
            playerStorageService.storePlayer(player.getLeague(), player);
         } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            System.err.println("Failed to deserialize player: " + e.getMessage());    
        }    
    }
}
