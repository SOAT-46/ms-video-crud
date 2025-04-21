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
        mockVideo = VideoModel.builder()
                .id(1L)
                .userName("joao_joao")
                .title("title")
                .status("PENDING")
                .build();
    }

    @Test
    void testGetVideosByUserName() {
        when(service.findByUserName("joao_joao")).thenReturn(Arrays.asList(mockVideo));

        List<VideoModel> result = controller.getVideosByUserName("joao_joao");

        assertEquals(1, result.size());
        assertEquals(mockVideo, result.get(0));
    }

    @Test
    void testSave() {
        when(service.create("joao_joao", null)).thenReturn(mockVideo);

        VideoModel result = controller.save("joao_joao", null);

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
