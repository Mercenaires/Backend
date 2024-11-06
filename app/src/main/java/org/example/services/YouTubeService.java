package org.example.services;

import org.example.models.VideoResult;
import org.example.models.YouTubeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeService {

    private final String API_KEY = "AIzaSyDnqJneEZ7oj8-vXgceE3nGKVxkt2a-wWI"; // Remplacez par votre clé API YouTube
    private final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search";
    private final RestTemplate restTemplate;

    // Injection de RestTemplate via le constructeur
    public YouTubeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<VideoResult> searchTopVideos(String gameName) {
        String url = UriComponentsBuilder.fromHttpUrl(YOUTUBE_API_URL)
                .queryParam("part", "snippet")
                .queryParam("q", gameName)
                .queryParam("type", "video")
                .queryParam("maxResults", 3) // Limite à trois vidéos
                .queryParam("order", "relevance")
                .queryParam("key", API_KEY)
                .toUriString();

        YouTubeResponse response = restTemplate.getForObject(url, YouTubeResponse.class);

        List<VideoResult> videos = new ArrayList<>();
        if (response != null && response.getItems() != null) {
            response.getItems().forEach(item -> videos.add(
                    new VideoResult(
                            item.getSnippet().getTitle(),
                            "https://www.youtube.com/embed/" + item.getId().getVideoId())));
        }

        return videos;
    }
}