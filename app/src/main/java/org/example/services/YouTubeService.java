package org.example.services;

import org.example.models.VideoResult;
import org.example.models.YouTubeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YouTubeService {

    private final String API_KEY = "AIzaSyDnqJneEZ7oj8-vXgceE3nGKVxkt2a-wWI"; // Remplace par ta clé API YouTube
    private final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search";

    public List<VideoResult> searchTopVideos(String query) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(YOUTUBE_API_URL)
                .queryParam("part", "snippet")
                .queryParam("q", query)
                .queryParam("type", "video")
                .queryParam("maxResults", 3)
                .queryParam("order", "viewCount")
                .queryParam("key", API_KEY)
                .toUriString();

        YouTubeResponse response = restTemplate.getForObject(url, YouTubeResponse.class);

        return response.getItems().stream()
                .map(item -> new VideoResult(
                        item.getSnippet().getTitle(),
                        "https://www.youtube.com/embed/" + item.getId().getVideoId()))
                .collect(Collectors.toList());
    }
}