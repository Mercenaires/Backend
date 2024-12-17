package org.example.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RAWGService {

    private final String RAWG_API_URL = "https://api.rawg.io/api/games";
    private final String API_KEY = "f3b7234c26f64859a127e93224980a8f";
    private final RestTemplate restTemplate;

    public RAWGService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getGameRating(String gameName) throws Exception {
        String url = UriComponentsBuilder.fromHttpUrl(RAWG_API_URL)
                .queryParam("key", API_KEY)
                .queryParam("search", gameName)
                .toUriString();

        RAWGResponse response = restTemplate.getForObject(url, RAWGResponse.class);

        if (response != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0).getRating();
        }

        throw new Exception("Aucun score trouvé pour le jeu : " + gameName);
    }
}
