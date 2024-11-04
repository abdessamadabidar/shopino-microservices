package com.shopino.payment_service.infrastructure.adapters.output.event.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shared.PaymentRequestNotification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentRequestNotification> kafkaTemplate;

    public void sendPaymentRequestNotification(PaymentRequestNotification paymentRequestNotification) {
        log.info(String.valueOf(paymentRequestNotification));
        kafkaTemplate.send("payment-topic", paymentRequestNotification.getOrderReference(), paymentRequestNotification);
    }
}
