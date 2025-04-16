package com.fiap.videos.controller;

import com.fiap.videos.model.VideoModel;
import com.fiap.videos.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoControllerTest {

    @InjectMocks
    private VideoController controller;

    @Mock
    private VideoService service;

    private VideoModel mockVideo;

    @BeforeEach
    void setUp() {
        mockVideo = new VideoModel();
        mockVideo.setId(1L);
        mockVideo.setUserId(100L);
        mockVideo.setStatus("PENDING");
    }

    @Test
    void testGetVideosByUserId() {
        when(service.findByUserId(100L)).thenReturn(Arrays.asList(mockVideo));

        List<VideoModel> result = controller.getVideosByUserId(100L);

        assertEquals(1, result.size());
        assertEquals(mockVideo, result.get(0));
    }

    @Test
    void testSaveVideo() {
        when(service.create(mockVideo)).thenReturn(mockVideo);

        VideoModel result = controller.saveVideo(mockVideo);

        assertNotNull(result);
        assertEquals(mockVideo, result);
    }

    @Test
    void testSaveStatusVideo() {
        when(service.updateStatus(1L, "\"COMPLETED\"")).thenReturn(mockVideo);

        VideoModel result = controller.saveStatusVideo(1L, "\"COMPLETED\"");

        assertEquals(mockVideo, result);
    }

    @Test
    void testDeleteVideo() {
        controller.deleteVideo(1L);
        verify(service).delete(1L);
    }
}
