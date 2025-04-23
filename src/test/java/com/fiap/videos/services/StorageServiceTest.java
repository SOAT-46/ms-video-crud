package com.fiap.videos.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StorageServiceTest {


    @Test
    void shouldUploadSuccessfully() throws IOException {
        // given
        final var file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("test.txt");
        when(file.getInputStream()).thenReturn(InputStream.nullInputStream());

        final var s3Client = mock(S3Client.class);
        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(null);

        final var service = new StorageService(s3Client);

        // when
        final var id = service.upload(file);

        // then
        assertNotNull(id, "id is not null null");
    }
}