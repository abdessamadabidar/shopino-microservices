package com.shopino.notification_service.application.ports.input;


import org.shared.OrderConfirmationNotification;
import org.shared.PaymentRequestNotification;

public interface PersistNotificationUseCase {
    OrderConfirmationNotification persistOrderConfirmationNotification(OrderConfirmationNotification notification);
    PaymentRequestNotification persistPaymentRequestNotification(PaymentRequestNotification paymentRequestNotification);

}
