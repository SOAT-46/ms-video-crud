package com.fiap.videos.producer.interfaces;

import com.fiap.videos.model.VideoModel;

public interface Sender {
    void send(final VideoModel video);
}
