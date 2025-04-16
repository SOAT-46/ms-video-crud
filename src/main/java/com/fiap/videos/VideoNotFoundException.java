package com.fiap.videos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(Long id) {
        super("Vídeo com ID " + id + " não encontrado");
    }
}
