package com.fiap.videos.controller;

import com.fiap.videos.controller.requests.CreateVideoRequest;
import com.fiap.videos.model.VideoModel;
import com.fiap.videos.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping("/{userId}")
    public List<VideoModel> getVideosByUserId(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }

    @PostMapping
    public VideoModel saveVideo(@RequestBody CreateVideoRequest video) {
        return service.create(video.toDomain());
    }

    @PutMapping("/saveStatus/{id}")
    public VideoModel saveStatusVideo(@PathVariable Long id, @RequestBody String status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVideo(@PathVariable Long id) {
        service.delete(id);
    }
}