package com.fiap.videos.producer.doubles;

import com.fiap.videos.model.VideoModel;
import com.fiap.videos.producer.interfaces.Sender;

public final class DummyMessageSender implements Sender {

    @Override
    public void send(final VideoModel video) {}
}
