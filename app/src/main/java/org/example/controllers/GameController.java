package org.example.controllers;

import org.example.services.RAWGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    private final RAWGService rawgService;

    @Autowired
    public GameController(RAWGService rawgService) {
        this.rawgService = rawgService;
    }

    @GetMapping("/game-score")
    public ResponseEntity<?> getGameScore(@RequestParam String gameName) {
        try {
            double score = rawgService.getGameRating(gameName);
            return ResponseEntity.ok(new GameScoreResponse(score));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Score introuvable pour le jeu : " + gameName);
        }
    }

    // Classe pour structurer la réponse de score
    public static class GameScoreResponse {
        private double score;

        public GameScoreResponse(double score) {
            this.score = score;
        }

        public double getScore() {
            return score;
        }
    }
}
