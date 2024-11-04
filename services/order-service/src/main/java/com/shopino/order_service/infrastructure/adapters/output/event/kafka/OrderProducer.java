package com.shopino.order_service.infrastructure.adapters.output.event.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shared.OrderConfirmationNotification;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmationNotification> kafkaTemplate;


    public void sendOrderConfirmation(OrderConfirmationNotification orderConfirmationNotification) {
        log.info(String.valueOf(orderConfirmationNotification));
        kafkaTemplate.send("order-topic", orderConfirmationNotification.getOrderReference(), orderConfirmationNotification);
    }
}
