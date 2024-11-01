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

<<<<<<< HEAD
        // Faire la requête à l'API YouTube
        YouTubeResponse response = restTemplate.getForObject(url, YouTubeResponse.class);

        // Retourner les 3 vidéos les plus vues sans filtre supplémentaire
        return response.getItems().stream()
                .limit(3) // Garder seulement les 3 premières vidéos
                .map(video -> video.getSnippet().getTitle())
                .collect(Collectors.toList());

    }
}
=======
    }
}
>>>>>>> 7232de910d3c88ea1646b7f756d1b91b18168518
