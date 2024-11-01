package org.example.controllers;

import org.example.services.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YouTubeController {

    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/search-videos")
    public List<String> searchVideos(@RequestParam String gameName) {
        return youTubeService.searchTopVideos(gameName);
    }
}
