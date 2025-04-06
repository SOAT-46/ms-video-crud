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

    @PostMapping("/saveStatus")
    public VideoModel saveStatusVideo(@RequestBody VideoModel video) {
        return videoRepository.save(video);
    }
}