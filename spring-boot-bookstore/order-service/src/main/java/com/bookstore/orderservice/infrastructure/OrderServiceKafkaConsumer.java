package com.bookstore.orderservice.infrastructure;

import com.bookstore.common.infrastructure.kafka.product_order.KafkaSendProductCreateCartItem;
import com.bookstore.common.utils.KafkaTopicUtils;
import com.bookstore.orderservice.application.es.cart_item.command.api.commands.AddCartItemCommand;
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
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceKafkaConsumer {

    @Autowired
    private CommandGateway commandGateway;

    @KafkaListener(topics = KafkaTopicUtils.TOPIC_ADD_TO_CART)
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
        KafkaSendProductCreateCartItem request = KafkaSendProductCreateCartItem.builder()
                .cartId((String) map.get("cartId"))
                .quantity(Integer.parseInt(map.get("quantity").toString()))
                .productId((String) map.get("productId"))
                .productName((String) map.get("productName"))
                .images((String) map.get("images"))
                .productShortDescription(map.get("productShortDescription").toString())
                .productPrice(Double.parseDouble(map.get("productPrice").toString()))
                .build();

        AddCartItemCommand command = new AddCartItemCommand(UUID.randomUUID().toString(),
                request.getCartId(),
                request.getQuantity(),
                request.getProductName(),
                request.getProductShortDescription(),
                request.getImages(),
                request.getProductId(),
                request.getProductPrice()
        );
        commandGateway.sendAndWait(command);
    }


}
