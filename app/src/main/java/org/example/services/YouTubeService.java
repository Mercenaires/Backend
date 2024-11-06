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

    private final String API_KEY = "YOUR_API_KEY"; // Remplacez par votre clé API YouTube
    private final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search";
    private final RestTemplate restTemplate;

    // Injection de RestTemplate via le constructeur
    public YouTubeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<VideoResult> searchTopGameplayVideos(String gameName) {
        String url = UriComponentsBuilder.fromHttpUrl(YOUTUBE_API_URL)
                .queryParam("part", "snippet")
                .queryParam("q", gameName + " gameplay")
                .queryParam("type", "video")
                .queryParam("maxResults", 50)
                .queryParam("order", "viewCount")
                .queryParam("key", API_KEY)
                .toUriString();

        YouTubeResponse response = restTemplate.getForObject(url, YouTubeResponse.class);

        List<VideoResult> gameplayVideos = new ArrayList<>();
        if (response != null && response.getItems() != null) {
            response.getItems().stream()
                    .filter(item -> {
                        String title = item.getSnippet().getTitle().toLowerCase();
                        return !title.contains("trailer") && !title.contains("teaser");
                    })
                    .limit(3)
                    .forEach(item -> gameplayVideos.add(
                            new VideoResult(
                                    item.getSnippet().getTitle(),
                                    "https://www.youtube.com/embed/" + item.getId().getVideoId())));
        }

        return gameplayVideos;
    }
}
