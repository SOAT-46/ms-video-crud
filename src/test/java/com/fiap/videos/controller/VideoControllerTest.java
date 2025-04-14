package com.fiap.videos.controller;

import com.fiap.videos.model.VideoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VideoControllerTest {
    private VideoController controller;
    private VideoModel video;

    @BeforeEach
    public void setUp() {
        controller = new VideoController();
    }

    @Test
    public void testGetVideosByUserId() {
        controller.getVideosByUserId(1L);
    }

    @Test
    public void testSaveVideo() {
        video.setUserId(1L);
        video.setId(1L);
        video.setTitle("Gato ronronando");
        video.setStatus("Em processamento");

        controller.saveVideo(video);
    }

    @Test
    public void testSaveStatusVideo() {
        controller.saveStatusVideo(1L, "Processado");
    }

    @Test
    public void  testDeleteVideo() {
        controller.deleteVideo(1L);
    }
}
