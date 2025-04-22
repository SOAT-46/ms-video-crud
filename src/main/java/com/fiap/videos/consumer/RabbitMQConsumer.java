package com.fiap.videos.consumer;

import com.fiap.videos.consumer.messages.UpdateStatus;
import com.fiap.videos.services.interfaces.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQConsumer {

    private final Video service;

    public RabbitMQConsumer(final Video service) {
        this.service = service;
    }

    @RabbitListener(queues = "${rabbitmq.update.queue.name}")
    public void listen(final UpdateStatus message) {
        log.info("Recebida mensagem: {}", message);
        this.service.updateStatus(message.id(), message.status());
        log.info("Status atualizado com sucesso");
    }
}
