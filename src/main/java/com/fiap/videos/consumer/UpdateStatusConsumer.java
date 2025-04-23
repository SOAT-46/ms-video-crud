package com.fiap.videos.consumer;

import com.fiap.videos.consumer.messages.UpdateStatus;
import com.fiap.videos.services.interfaces.Video;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateStatusConsumer {

    private final Video service;

    public UpdateStatusConsumer(final Video service) {
        this.service = service;
    }

    @SqsListener("update-video-status")
    public void listen(final UpdateStatus message) {
        log.info("Recebida mensagem: {}", message);
        this.service.updateStatus(message.id(), message.status());
        log.info("Status atualizado com sucesso");
    }
}
