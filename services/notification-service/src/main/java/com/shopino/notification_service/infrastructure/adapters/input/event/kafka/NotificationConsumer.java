package com.shopino.notification_service.infrastructure.adapters.input.event.kafka;

import com.shopino.notification_service.application.ports.input.PersistNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.shared.OrderConfirmationNotification;
import org.shared.PaymentRequestNotification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final PersistNotificationUseCase persistNotificationUseCase;



    @KafkaListener(
            topics = {"order-topic", "payment-topic"},
            groupId = "notifications",
            id = "orderListener"
    )
    public void consume(OrderConfirmationNotification orderConfirmationNotification) {
        persistNotificationUseCase.persistOrderConfirmationNotification(orderConfirmationNotification);
    }


    @KafkaListener(
            topics = {"payment-topic"},
            groupId = "notifications",
            id = "paymentListener"
    )
    public void consume(PaymentRequestNotification paymentRequestNotification) {
        persistNotificationUseCase.persistPaymentRequestNotification(paymentRequestNotification);
    }

}
