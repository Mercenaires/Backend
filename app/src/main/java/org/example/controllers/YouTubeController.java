package org.example.controllers;

import org.example.models.VideoResult;
import org.example.services.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class YouTubeController {

    private final YouTubeService youTubeService;

    @Autowired
    public YouTubeController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    @GetMapping("/search-videos")
    public List<VideoResult> searchVideos(@RequestParam String gameName) {
        return youTubeService.searchTopGameplayVideos(gameName);
    }
}
