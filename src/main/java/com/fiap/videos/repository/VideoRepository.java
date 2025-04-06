package com.fiap.videos.repository;

import com.fiap.videos.model.VideoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoModel, Long> {
    List<VideoModel> findByUserId(Long userId);
}