package com.footballdle.guessingService.Model;

public class GuessRequest {
    private String playerName;
    private String sessionId;
    public String getSessionId() {
        return sessionId;
    }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
}
