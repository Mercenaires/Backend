package org.example.models;

public class VideoResult {
    private String title;
    private String embedUrl;

    public VideoResult(String title, String embedUrl) {
        this.title = title;
        this.embedUrl = embedUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }
}
