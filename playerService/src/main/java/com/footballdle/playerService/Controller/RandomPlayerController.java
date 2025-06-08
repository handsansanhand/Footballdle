package com.footballdle.playerService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.footballdle.playerService.Model.Player;
import com.footballdle.playerService.Repository.PlayerRepository;

@RestController
@RequestMapping("/api/player")
public class RandomPlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/random")
    public Player getRandomPlayer() {
        return playerRepository.findRandomPlayer();
    }
}
