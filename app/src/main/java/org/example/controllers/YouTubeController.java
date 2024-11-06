package org.example.controllers;

import org.example.models.VideoResult;
import org.example.services.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class YouTubeController {

    private final YouTubeService youTubeService;

    @Autowired
    public YouTubeController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    /**
     * Recherche trois vidéos YouTube pour un terme de jeu donné.
     * 
     * @param gameName Nom du jeu pour lequel chercher des vidéos.
     * @return Une réponse HTTP contenant une liste de résultats de vidéos.
     */
    @GetMapping("/search-videos")
    public ResponseEntity<?> searchVideos(@RequestParam String gameName) {
        try {
            List<VideoResult> videos = youTubeService.searchTopVideos(gameName);
            return ResponseEntity.ok(videos);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des vidéos YouTube: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors de la récupération des vidéos YouTube.");
        }
    }
}
