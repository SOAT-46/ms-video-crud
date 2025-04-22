package com.fiap.videos.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface Storage {
    String upload(MultipartFile video);
}
