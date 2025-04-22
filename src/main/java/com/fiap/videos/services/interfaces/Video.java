package com.fiap.videos.services.interfaces;

import com.fiap.videos.model.VideoModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Video {
    List<VideoModel> findByUserName(String userName);
    VideoModel create(String userName, MultipartFile file);
    VideoModel updateStatus(Long id, String status);
    void delete(Long id);
}
