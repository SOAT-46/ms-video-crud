package com.fiap.videos.controller.requests;

import com.fiap.videos.model.VideoModel;

public record CreateVideoRequest(Long userId, String title) {
    public VideoModel toDomain() {
        return VideoModel.builder()
                .title(title)
                .userId(userId)
                .build();
    }
}
