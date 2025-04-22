package com.fiap.videos.services;


import com.fiap.videos.VideoNotFoundException;
import com.fiap.videos.model.VideoModel;
import com.fiap.videos.producer.doubles.DummyMessageSender;
import com.fiap.videos.repositories.doubles.DummyVideoRepository;
import com.fiap.videos.repositories.doubles.InMemoryVideoRepository;
import com.fiap.videos.repository.VideoRepository;
import com.fiap.videos.services.doubles.DummyStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    private VideoModel mockVideo;

    @BeforeEach
    void setUp() {
        mockVideo = new VideoModel();
        mockVideo.setId(1L);
        mockVideo.setUserName("joao_joao");
        mockVideo.setStatus("PENDING");
    }

    @Test
    void testFindByUserName() {
        // given
        final var list = List.of(mockVideo);
        final var repository = new InMemoryVideoRepository(list);
        final var service = new VideoService(repository, null, null);

        // when
        var result = service.findByUserName("joao_joao");

        // then
        assertEquals(1, result.size());
        assertEquals(mockVideo, result.get(0));
    }

    @Test
    void testCreateVideo() {
        // given
        final var list = List.of(mockVideo);
        final var repository = new InMemoryVideoRepository(list);
        final var messageSender = new DummyStorageService();
        final var storageService = new DummyMessageSender();
        final var service = new VideoService(repository, storageService, messageSender);

        final var file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("test.txt");

        // when
        var result = service.create("joao_joao", file);

        assertNotNull(result);
    }

    @Test
    void testUpdateStatus_shouldUpdateAndReturn() {
        // given
        final var list = List.of(mockVideo);
        final var repository = new InMemoryVideoRepository(list);
        final var messageSender = new DummyStorageService();
        final var storageService = new DummyMessageSender();

        final var service = new VideoService(repository, storageService, messageSender);

        // when
        var updated = service.updateStatus(1L, "\"COMPLETED\"");

        // then
        assertEquals("COMPLETED", updated.getStatus());
    }

    @Test
    void testUpdateStatus_shouldThrowWhenNotFound() {
        // given
        final var repository = new DummyVideoRepository();
        final var messageSender = new DummyStorageService();
        final var storageService = new DummyMessageSender();
        final var service = new VideoService(repository, storageService, messageSender);

        assertThrows(VideoNotFoundException.class, () ->
                service.updateStatus(99L, "\"COMPLETED\""));
    }

    @Test
    void testDelete_shouldCallRepository() {
        // given
        final var repository = mock(VideoRepository.class);
        final var messageSender = new DummyStorageService();
        final var storageService = new DummyMessageSender();
        final var service = new VideoService(repository, storageService, messageSender);

        // when
        service.delete(1L);

        // then
        verify(repository).deleteById(1L);
    }
}
