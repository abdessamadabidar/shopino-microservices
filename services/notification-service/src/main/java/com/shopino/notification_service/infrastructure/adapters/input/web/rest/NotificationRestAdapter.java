package com.shopino.notification_service.infrastructure.adapters.input.web.rest;

import com.shopino.notification_service.application.ports.input.GetAllNotificationsUseCase;
import com.shopino.notification_service.application.ports.input.GetPaginatedOrderConfirmationNotificationsUseCase;
import com.shopino.notification_service.application.ports.input.GetPaginatedPaymentNotificationsUseCase;
import lombok.AllArgsConstructor;
import org.shared.OrderConfirmationNotification;
import org.shared.PageResponse;
import org.shared.PaymentRequestNotification;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationRestAdapter {
    private final GetAllNotificationsUseCase getAllNotificationsUseCase;
    private final GetPaginatedOrderConfirmationNotificationsUseCase getPaginatedOrderConfirmationNotificationsUseCase;
    private final GetPaginatedPaymentNotificationsUseCase getPaginatedPaymentNotificationsUseCase;

    @KafkaListener(
            topics = {"order-topic"},
            groupId = "notifications"
    )
    @GetMapping
    public ResponseEntity<List<OrderConfirmationNotification>> notifications() {
        return ResponseEntity.ok(getAllNotificationsUseCase.findAllNotifications());
    }

    @GetMapping(params = {"page", "size"}, path = "/order-confirmation")
    public ResponseEntity<PageResponse<OrderConfirmationNotification>> getPaginatedOrderConfirmationNotifications(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseEntity.ok(
                getPaginatedOrderConfirmationNotificationsUseCase
                        .getPaginatedOrderConfirmationNotifications(page, size)
        );
    }


    @GetMapping(params = {"page", "size"}, path = "/payment-request")
    public ResponseEntity<PageResponse<PaymentRequestNotification>> getPaginatedPaymentRequestNotifications(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseEntity.ok(
                getPaginatedPaymentNotificationsUseCase
                        .getPaginatedPaymentNotifications(page, size)
        );
    }
}
