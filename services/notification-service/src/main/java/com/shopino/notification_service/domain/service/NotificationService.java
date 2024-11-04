package com.shopino.notification_service.domain.service;

import com.shopino.notification_service.application.ports.input.GetAllNotificationsUseCase;
import com.shopino.notification_service.application.ports.input.GetPaginatedOrderConfirmationNotificationsUseCase;
import com.shopino.notification_service.application.ports.input.GetPaginatedPaymentNotificationsUseCase;
import com.shopino.notification_service.application.ports.input.PersistNotificationUseCase;
import com.shopino.notification_service.application.ports.output.NotificationOutputPort;
import lombok.RequiredArgsConstructor;
import org.shared.OrderConfirmationNotification;
import org.shared.PageInfo;
import org.shared.PageResponse;
import org.shared.PaymentRequestNotification;
import org.springframework.data.domain.Page;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NotificationService implements
        PersistNotificationUseCase,
        GetAllNotificationsUseCase,
        GetPaginatedOrderConfirmationNotificationsUseCase,
        GetPaginatedPaymentNotificationsUseCase
{
    private final NotificationOutputPort notificationOutputPort;


    @Override
    public OrderConfirmationNotification persistOrderConfirmationNotification(OrderConfirmationNotification orderConfirmationNotification) {
        return notificationOutputPort.saveNotification(
                orderConfirmationNotification
        );
    }

    @Override
    public PaymentRequestNotification persistPaymentRequestNotification(PaymentRequestNotification paymentRequestNotification) {
        return notificationOutputPort.saveNotification(
                paymentRequestNotification
        );
    }


    @Override
    public List<OrderConfirmationNotification> findAllNotifications() {
        return notificationOutputPort.findAllNotifications();
    }


    @Override
    public PageResponse<OrderConfirmationNotification> getPaginatedOrderConfirmationNotifications(int page, int size) {
        Page<OrderConfirmationNotification> orderConfirmationNotificationPage =
                notificationOutputPort.findAllOrderConfirmationNotifications(page, size);

        return PageResponse.<OrderConfirmationNotification>builder()
                .content(orderConfirmationNotificationPage.getContent())
                .pageInfo(
                        PageInfo
                                .builder()
                                .totalPages(orderConfirmationNotificationPage.getTotalPages())
                                .totalElements(orderConfirmationNotificationPage.getTotalElements())
                                .currentPage(orderConfirmationNotificationPage.getNumber() + 1)
                                .size(orderConfirmationNotificationPage.getSize())
                                .hasNext(orderConfirmationNotificationPage.hasNext())
                                .hasPrevious(orderConfirmationNotificationPage.hasPrevious())
                                .build()
                )
                .build();
    }

    @Override
    public PageResponse<PaymentRequestNotification> getPaginatedPaymentNotifications(int page, int size) {
        Page<PaymentRequestNotification> paymentRequestNotificationPage =
                notificationOutputPort.findAllPaymentRequestNotifications(page, size);

        return PageResponse.<PaymentRequestNotification>builder()
                .content(paymentRequestNotificationPage.getContent())
                .pageInfo(
                        PageInfo
                                .builder()
                                .totalPages(paymentRequestNotificationPage.getTotalPages())
                                .totalElements(paymentRequestNotificationPage.getTotalElements())
                                .currentPage(paymentRequestNotificationPage.getNumber() + 1)
                                .size(paymentRequestNotificationPage.getSize())
                                .hasNext(paymentRequestNotificationPage.hasNext())
                                .hasPrevious(paymentRequestNotificationPage.hasPrevious())
                                .build()
                )
                .build();
    }
}
