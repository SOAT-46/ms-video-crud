package com.fiap.videos.services;


import com.fiap.videos.VideoNotFoundException;
import com.fiap.videos.model.VideoModel;
import com.fiap.videos.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @InjectMocks
    private VideoService service;

    @Mock
    private VideoRepository videoRepository;

    private VideoModel mockVideo;

    @BeforeEach
    void setUp() {
        mockVideo = new VideoModel();
        mockVideo.setId(1L);
        mockVideo.setUserId(100L);
        mockVideo.setStatus("PENDING");
    }

    @Test
    void testFindByUserId() {
        when(videoRepository.findByUserId(100L)).thenReturn(Arrays.asList(mockVideo));

        var result = service.findByUserId(100L);

        assertEquals(1, result.size());
        assertEquals(mockVideo, result.get(0));
    }

    @Test
    void testCreateVideo() {
        when(videoRepository.save(mockVideo)).thenReturn(mockVideo);

        var result = service.create(mockVideo);

        assertNotNull(result);
        assertEquals(mockVideo, result);
    }

    @Test
    void testUpdateStatus_shouldUpdateAndReturn() {
        when(videoRepository.findById(1L)).thenReturn(Optional.of(mockVideo));
        when(videoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        var updated = service.updateStatus(1L, "\"COMPLETED\"");

        assertEquals("COMPLETED", updated.getStatus());
    }

    @Test
    void testUpdateStatus_shouldThrowWhenNotFound() {
        when(videoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(VideoNotFoundException.class, () ->
                service.updateStatus(99L, "\"COMPLETED\""));
    }

    @Test
    void testDelete_shouldCallRepository() {
        service.delete(1L);
        verify(videoRepository).deleteById(1L);
    }
}
