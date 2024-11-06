package org.example.servicesT;

import org.example.models.VideoResult;
import org.example.models.YouTubeResponse;
import org.example.services.YouTubeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class YouTubeServiceTest {

    @InjectMocks
    private YouTubeService youTubeService;

    @Mock
    private RestTemplate restTemplate;

    public YouTubeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchTopGameplayVideos() {
        // Mock de la réponse de l’API YouTube
        YouTubeResponse response = new YouTubeResponse();
        YouTubeResponse.Item item1 = new YouTubeResponse.Item();
        item1.setSnippet(new YouTubeResponse.Snippet());
        item1.getSnippet().setTitle("Gameplay 1");
        item1.setId(new YouTubeResponse.Id());
        item1.getId().setVideoId("abc123");

        response.setItems(Arrays.asList(item1, item1, item1));

        // Utilisation de anyString() pour matcher toute URL
        when(restTemplate.getForObject(anyString(), eq(YouTubeResponse.class))).thenReturn(response);

        List<VideoResult> results = youTubeService.searchTopGameplayVideos("Minecraft");

        assertNotNull(results);
        assertEquals(3, results.size()); // Vérifie que le service limite bien les résultats à 3
    }
}
