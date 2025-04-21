package com.fiap.videos.controller;

import com.fiap.videos.controller.requests.CreateVideoRequest;
import com.fiap.videos.model.VideoModel;
import com.fiap.videos.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(
            path = "{userId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VideoModel save(
            @PathVariable("userId") final Long userId,
            @RequestParam(value = "file") final MultipartFile file) {
        return service.create(userId, file);
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