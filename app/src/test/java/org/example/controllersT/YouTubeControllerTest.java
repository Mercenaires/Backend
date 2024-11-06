package org.example.controllersT;

import org.example.App;
import org.example.models.VideoResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class YouTubeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {
        String gameName = "Sonic";
        String url = "http://localhost:" + port + "/search-videos?gameName=" + gameName;

        // Envoyer une requête GET au contrôleur
        ResponseEntity<VideoResult[]> response = restTemplate.getForEntity(url, VideoResult[].class);

        // Vérifier que le statut HTTP est 200 (OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Vérifier que le contenu de la réponse contient des vidéos (optionnel)
        VideoResult[] videos = response.getBody();
        assertThat(videos).isNotNull();
        assertThat(videos.length).isGreaterThan(0);
    }
}
