package com.bookstore.notificationservice.infrastructure.kafka;

import com.bookstore.common.infrastructure.kafka.product_order.KafkaSendProductCreateCartItem;
import com.bookstore.common.utils.KafkaTopicUtils;
import com.bookstore.common.utils.notification.NotificationDestination;
import com.bookstore.notificationservice.domain.service.ISendNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceKafkaConsumer {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private ISendNotificationService service;

    @KafkaListener(topics = KafkaTopicUtils.TOPIC_SEND_NOTIFICATION_FROM_ORDER)
    public void addProductToCart(String kafkaMessage) {
        log.info("Kafka Message : " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {
            });
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        if(Objects.equals(map.get("destination").toString(), NotificationDestination.EMAIL.name())){
            service.sendGmail();
        }

    }
}