package org.example.controllersT;

import org.example.models.VideoResult;
import org.example.services.YouTubeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class YouTubeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private YouTubeService youTubeService;

    @Test
    public void testSearchVideos() throws Exception {
        // Mock the service response
        Mockito.when(youTubeService.searchTopVideos("GameName"))
                .thenReturn(List.of(new VideoResult("Title1", "https://www.youtube.com/embed/video1")));

        // Execute the test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/search-videos")
                .param("gameName", "GameName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[0].videoUrl").value("https://www.youtube.com/embed/video1"));
    }

    @Test
    public void testSearchVideosNoResults() throws Exception {
        // Mock the service response
        Mockito.when(youTubeService.searchTopVideos("UnknownGame"))
                .thenReturn(List.of());

        // Execute the test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/search-videos")
                .param("gameName", "UnknownGame")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testSearchVideosServiceError() throws Exception {
        // Mock the service to throw an exception
        Mockito.when(youTubeService.searchTopVideos("ErrorGame"))
                .thenThrow(new RuntimeException("Service error"));

        // Execute the test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/search-videos")
                .param("gameName", "ErrorGame")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur lors de la récupération des vidéos."));
    }
}