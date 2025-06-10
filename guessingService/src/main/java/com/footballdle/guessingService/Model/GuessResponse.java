package com.footballdle.guessingService.Model;

public class GuessResponse {
    private String sessionId;
    private Player guessedPlayer;
    private Player correctPlayer;
   public Player getCorrectPlayer() {
       return correctPlayer;
   }
   public Player getGuessedPlayer() {
       return guessedPlayer;
   }
   public String getSessionId() {
       return sessionId;
   }
   public void setGuessedPlayer(Player guessedPlayer) {
       this.guessedPlayer = guessedPlayer;
   }
   public void setCorrectPlayer(Player correctPlayer) {
       this.correctPlayer = correctPlayer;
   }

   public void setSessionId(String sessionId) {
       this.sessionId = sessionId;
   }
}
