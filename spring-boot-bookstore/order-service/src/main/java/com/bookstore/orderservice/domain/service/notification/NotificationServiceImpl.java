package com.bookstore.orderservice.domain.service.notification;

import com.bookstore.common.infrastructure.kafka.notification.KafkaNotificationDeviceRequest;
import com.bookstore.common.infrastructure.kafka.notification.KafkaNotificationEmailRequest;
import com.bookstore.common.infrastructure.kafka.notification.KafkaNotificationRequest;
import com.bookstore.common.utils.KafkaTopicUtils;
import com.bookstore.orderservice.infrastructure.OrderServiceKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private OrderServiceKafkaProducer kafkaProducer;

    @Override
    public void send(KafkaNotificationRequest request) {
        kafkaProducer.send(KafkaTopicUtils.TOPIC_SEND_NOTIFICATION_FROM_ORDER, request);
    }
}
