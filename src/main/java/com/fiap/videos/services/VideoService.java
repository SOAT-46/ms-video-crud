package com.fiap.videos.services;

import com.fiap.videos.VideoNotFoundException;
import com.fiap.videos.model.VideoModel;
import com.fiap.videos.producer.MessageSender;
import com.fiap.videos.repository.VideoRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final MessageSender messageSender;
    private final StorageService storageService;

    public VideoService(
            final VideoRepository videoRepository,
            final MessageSender messageSender,
            final StorageService storageService) {
        this.videoRepository = videoRepository;
        this.messageSender = messageSender;
        this.storageService = storageService;
    }

    public List<VideoModel> findByUserName(String userName) {
        return videoRepository.findByUserName(userName);
    }

    @SneakyThrows
    public VideoModel create(final String userName, final MultipartFile file) {
        final var key = this.storageService.upload(file);
        final var video = VideoModel
                .builder()
                .userName(userName)
                .videoKey(key)
                .title(file.getOriginalFilename())
                .status("IN_PROCESS")
                .build();

        final var newEntity = videoRepository.save(video);
        this.messageSender.Send(newEntity);
        return newEntity;
    }

    public VideoModel updateStatus(Long id, String status) {
        VideoModel video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(id));

        video.setStatus(status.replace("\"", ""));
        return videoRepository.save(video);
    }

    public void delete(Long id) {
        videoRepository.deleteById(id);
    }
}
