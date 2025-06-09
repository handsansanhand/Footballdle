package com.footballdle.guessingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/*A string is sent, to the guess controller, this then sends that string to the player service to lookup the player */
@Service
public class GuessPublishService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${guess.to.player.topic:guess-topic}")
    private String topicName;

    @Autowired
    public GuessPublishService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendGuessRequest(String message) {
        kafkaTemplate.send(topicName, message);
    }
}