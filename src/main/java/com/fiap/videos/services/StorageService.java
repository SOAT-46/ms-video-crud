package com.fiap.videos.services;

import com.fiap.videos.services.interfaces.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class StorageService implements Storage {

    @Value("${aws.s3.bucket-name}")
    private String bucket;

    private final S3Client client;

    public StorageService(final S3Client client) {
        this.client = client;
    }

    public String upload(final MultipartFile video) {
        final var key = UUID.randomUUID().toString();
        final var fileName = format("%s-%s", key, video.getOriginalFilename());
        this.uploadToS3(fileName, video);
        return fileName;
    }

    private void uploadToS3(final String filename, final MultipartFile video) {
        try (var file = video.getInputStream()) {
            final var request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(filename)
                    .contentType(video.getContentType())
                    .contentLength(video.getSize())
                    .build();
            final var requestBody = RequestBody.fromBytes(file.readAllBytes());
            this.client.putObject(request, requestBody);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possivel realizar o upload do documento");
        }
    }
}
