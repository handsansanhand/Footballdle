package com.footballdle.guessingService.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.footballdle.guessingService.Model.GuessResponse;

/*Each session will have their own session id */
@Service
public class GuessResponseManager {
    private final Map<String, CompletableFuture<GuessResponse>> pendingResponses = new ConcurrentHashMap<>();

public CompletableFuture<GuessResponse> registerSession(String sessionId) {
    CompletableFuture<GuessResponse> future = new CompletableFuture<>();
    pendingResponses.put(sessionId, future);
    return future;
}

public void completeSession(String sessionId, GuessResponse response) {
    CompletableFuture<GuessResponse> future = pendingResponses.remove(sessionId);
    if (future != null) {
        future.complete(response);
    }
}
    
}
