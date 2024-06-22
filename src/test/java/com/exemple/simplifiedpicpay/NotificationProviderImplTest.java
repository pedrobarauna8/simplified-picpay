package com.exemple.simplifiedpicpay;

import com.exemple.simplifiedpicpay.infra.service.impl.NotificationProviderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NotificationProviderImplTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private NotificationProviderImpl notificationService;

    private final String topicName = "testTopic";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationProviderImpl(kafkaTemplate, topicName);
    }

    @Test
    public void sendNotification_sendsEmptyMessageToKafka() {
        notificationService.sendNotification();

        verify(kafkaTemplate, times(1)).send(topicName, "");
    }
}
