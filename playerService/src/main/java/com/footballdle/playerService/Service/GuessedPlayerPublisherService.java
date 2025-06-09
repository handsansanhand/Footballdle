package com.footballdle.playerService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.playerService.Model.Player;

@Service
public class GuessedPlayerPublisherService {
     private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${player.topic.name:guessed-player-topic}")
    private String topicName;

       @Autowired
    public GuessedPlayerPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }  

    public void publishGuessedPlayer(Player player) {
        String playerJson = convertPlayerToJson(player);
        kafkaTemplate.send(topicName, playerJson);
    }


    private String convertPlayerToJson(Player player) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(player);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize player", e);
        }
    }

}
