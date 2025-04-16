package com.fiap.videos.services;

import com.fiap.videos.VideoNotFoundException;
import com.fiap.videos.model.VideoModel;
import com.fiap.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<VideoModel> findByUserId(Long userId) {
        return videoRepository.findByUserId(userId);
    }

    public VideoModel create(VideoModel video) {
        return videoRepository.save(video);
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
