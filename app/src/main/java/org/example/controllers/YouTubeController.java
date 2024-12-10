package org.example.controllers;

import org.example.models.VideoResult;
import org.example.services.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/search-videos")
    public ResponseEntity<?> searchVideos(@RequestParam String gameName) {
        try {
            List<VideoResult> videos = youTubeService.searchTopVideos(gameName);
            return ResponseEntity.ok(videos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des vidéos.");
        }
    }
}
