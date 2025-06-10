package com.footballdle.playerService.Model;

public class GuessRequest {
     private String playerName;
    private String sessionId;
    private String league; 

    public String getSessionId() { return sessionId; }
    public String getPlayerName() { return playerName; }
    public String getLeague() { return league; }

    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public void setLeague(String league) { this.league = league; }
}


