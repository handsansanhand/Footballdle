package com.footballdle.guessingService.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/*This class should connect to the kafka container, subscribe to the queue, and maybe store the answer in cache */
@Service
public class PlayerConsumerService {
    @KafkaListener(topics = "players-topic", groupId = "guessing-service-group") 
    public void listen(String message) {
         System.out.println("Received message on players-topic: " + message);
    }
}
