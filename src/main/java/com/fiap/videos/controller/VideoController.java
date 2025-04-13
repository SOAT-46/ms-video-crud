package com.fiap.videos.controller;

import com.fiap.videos.model.VideoModel;
import com.fiap.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/{userId}")
    public List<VideoModel> getVideosByUserId(@PathVariable Long userId) {
        return videoRepository.findByUserId(userId);
    }

    @PostMapping("/save")
    public VideoModel saveVideo(@RequestBody VideoModel video) {
        return videoRepository.save(video);
    }

    @PutMapping("/saveStatus/{id}")
    public VideoModel saveStatusVideo(@PathVariable Long id, @RequestBody String status) {
        VideoModel video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vídeo não encontrado"));

        video.setStatus(status);

        return videoRepository.save(video);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVideo(@PathVariable Long id) {
        videoRepository.deleteById(id);
    }
}