package com.footballdle.guessingService.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballdle.guessingService.Model.GuessResponse;
import com.footballdle.guessingService.Model.Player;

/*Helper class which responds with the results of a guess
 */
public class GuessHandler {
    
    /*Function which generates a json filled with answers
     * The guess response should have a guessedPlayer and a correctPlayer
     * Go through each key value pair, determine if the guess is higher / lower, right or wrong
     */
    public static Map<String, String> generateAnswerFromGuess(GuessResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        
        Player guessedPlayer = response.getGuessedPlayer();
        Player correctPlayer = response.getCorrectPlayer();

        JsonNode guessedNode = mapper.valueToTree(guessedPlayer);
        JsonNode correctNode = mapper.valueToTree(correctPlayer);

        Map<String, String> results = new HashMap<>();

        //go through each field
        Iterator<String> fieldNames = guessedNode.fieldNames();
        while(fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            System.out.println("Comparing " + fieldName);
            //get each value
            JsonNode guessedValue = guessedNode.get(fieldName);
            JsonNode correctValue = correctNode.get(fieldName);
            //if its a number, determine if its higher or lower or correct, if its a string, determine if its right or wrong
            if(guessedValue.isNumber()) {
                System.out.println("Comparing " + guessedValue.asInt() + " and " + correctValue.asInt() );
                results.put(fieldName, compare(guessedValue.asInt(), correctValue.asInt()));
            } else {
                System.out.println("Comparing " + guessedValue + " and " + correctValue);
                results.put(fieldName, rightOrWrong(guessedValue.asText(), correctValue.asText()));
            }
        }
        System.out.println("Final result : " + results);
        return results;

    }

    private static String compare(int value1, int value2) {
        String returnString = "";
        if(value1<value2){
            returnString = "Higher";
        } else if (value2<value1){
            returnString = "Lower";
        }
        else {
            returnString = "Correct";
        }
        return returnString;
    }
    private static String rightOrWrong(String s1, String s2) {
        if(s1.equals(s2)) {
            return "Correct";
        }
        else {
            return "Incorrect";
        }
    }
}
