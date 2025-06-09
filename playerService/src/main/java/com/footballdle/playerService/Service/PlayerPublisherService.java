package com.footballdle.playerService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.playerService.Model.Player;

/*Class which creates a kafka template (key, value pair)
 * And has functionality to send the randomly generated footballers to the kafka topic
 */
@Service
public class PlayerPublisherService {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${player.topic.name:players-topic}")
    private String topicName;

    
    @Autowired
    public PlayerPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }        
    public void publishPlayer(Player player) {
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
