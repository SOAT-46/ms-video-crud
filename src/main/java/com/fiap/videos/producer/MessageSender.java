package com.fiap.videos.producer;

import com.fiap.videos.model.VideoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender {

    private final RabbitTemplate template;
    private final Queue queue;

    @Autowired
    public MessageSender(final RabbitTemplate rabbitTemplate, final Queue queue) {
        template = rabbitTemplate;
        this.queue = queue;
    }

    public void Send(VideoModel video) {
        try {
            this.template.convertAndSend(queue.getName(), video);
            log.info("✅ Dado enviado para a fila com sucesso");
        } catch (Exception e) {
            log.error("❌ Falha ao enviar mensagem para a fila", e);
        }
    }
}
