package com.shopino.notification_service.application.ports.output;

import org.shared.OrderConfirmationNotification;
import org.shared.PaymentRequestNotification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotificationOutputPort {
    OrderConfirmationNotification saveNotification(OrderConfirmationNotification notification);
    PaymentRequestNotification saveNotification(PaymentRequestNotification paymentRequestNotification);
    List<OrderConfirmationNotification> findAllNotifications();
    Page<PaymentRequestNotification> findAllPaymentRequestNotifications(int page, int size);
    Page<OrderConfirmationNotification> findAllOrderConfirmationNotifications(int page, int size);


}
