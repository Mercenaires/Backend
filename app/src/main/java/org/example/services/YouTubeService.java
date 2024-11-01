package org.example.services;

import org.example.models.YouTubeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YouTubeService {

    private final String API_KEY = "AIzaSyDnqJneEZ7oj8-vXgceE3nGKVxkt2a-wWI"; // Remplace par ta clé API
    private final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search";

    public List<String> searchTopVideos(String query) {
        RestTemplate restTemplate = new RestTemplate();

        // Construire l'URL de la requête
        // Construire l'URL de la requête sans ajouter de "gameplay" ni filtrer par
        // catégorie
        String url = UriComponentsBuilder.fromHttpUrl(YOUTUBE_API_URL)
                .queryParam("part", "snippet")
                .queryParam("q", query) // Utiliser uniquement la requête de l'utilisateur
                .queryParam("type", "video")
                .queryParam("maxResults", 50) // Utiliser un nombre plus élevé pour obtenir plus de résultats initiaux
                .queryParam("order", "viewCount") // Trier par nombre de vues
                .queryParam("key", API_KEY)
                .toUriString();

    }
}
