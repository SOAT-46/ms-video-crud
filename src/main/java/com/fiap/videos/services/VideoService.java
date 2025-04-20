package com.fiap.videos.services;

import com.fiap.videos.VideoNotFoundException;
import com.fiap.videos.model.VideoModel;
import com.fiap.videos.producer.MessageSender;
import com.fiap.videos.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final MessageSender messageSender;

    public VideoService(
            final VideoRepository videoRepository,
            final MessageSender messageSender) {
        this.videoRepository = videoRepository;
        this.messageSender = messageSender;
    }

    public List<VideoModel> findByUserId(Long userId) {
        return videoRepository.findByUserId(userId);
    }

    public VideoModel create(VideoModel video) {
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
