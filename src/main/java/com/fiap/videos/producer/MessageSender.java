package com.fiap.videos.producer;

import com.fiap.videos.model.VideoModel;
import com.fiap.videos.producer.interfaces.Sender;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender implements Sender {

    private final SqsTemplate template;
    private final String url;

    public MessageSender(final SqsTemplate template, @Value("${aws.sqs.queue}") final String url) {
        this.template = template;
        this.url = url;
    }

    public void send(final VideoModel video) {
        try {
            final var sqsUrl = this.url + "/process-video";
//            final var request = SendMessageRequest
//                    .builder()
//                    .queueUrl(sqsUrl)
//                    .messageBody(video.toString())
//                    .build();
            this.template.send(sqsUrl, video);
            log.info("✅ Dado enviado para a fila com sucesso");
        } catch (Exception e) {
            log.error("❌ Falha ao enviar mensagem para a fila", e);
        }
    }
}
