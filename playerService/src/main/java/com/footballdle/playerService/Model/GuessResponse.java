package com.footballdle.playerService.Model;

public class GuessResponse {
    private String sessionId;
    private Player player;

   public Player getPlayer() {
       return player;
   }
   public String getSessionId() {
       return sessionId;
   }
   public void setPlayer(Player player) {
       this.player = player;
   }
   public void setSessionId(String sessionId) {
       this.sessionId = sessionId;
   }
}
