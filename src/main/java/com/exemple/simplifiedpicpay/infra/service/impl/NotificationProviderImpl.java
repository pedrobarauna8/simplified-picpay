package com.exemple.simplifiedpicpay.infra.service.impl;

import com.exemple.simplifiedpicpay.core.provider.NotificationProvider;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Log4j2
@Service
public class NotificationProviderImpl implements NotificationProvider {

    KafkaTemplate<String, String> kafkaTemplate;
    String topicName;

    public NotificationProviderImpl(KafkaTemplate<String, String> kafkaTemplate,
                                    @Value("${topic.name.producer}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendNotification() {
        log.info("Topico enviado");
        kafkaTemplate.send(topicName, "");
    }
}
