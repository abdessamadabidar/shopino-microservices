package com.shopino.notification_service.infrastructure.adapters.output.database;

import com.shopino.notification_service.application.ports.output.NotificationOutputPort;
import com.shopino.notification_service.infrastructure.adapters.output.database.repository.OrderConfirmationNotificationMongoRepository;
import com.shopino.notification_service.infrastructure.adapters.output.database.repository.PaymentRequestNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shared.OrderConfirmationNotification;
import org.shared.PaymentRequestNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPersistenceAdapter implements NotificationOutputPort {

    private final OrderConfirmationNotificationMongoRepository orderConfirmationNotificationMongoRepository;
    private final PaymentRequestNotificationRepository paymentRequestNotificationRepository;
    @Override
    public OrderConfirmationNotification saveNotification(OrderConfirmationNotification orderConfirmationNotification) {
        return orderConfirmationNotificationMongoRepository.save(orderConfirmationNotification);
    }

    @Override
    public PaymentRequestNotification saveNotification(PaymentRequestNotification paymentRequestNotification) {
        return paymentRequestNotificationRepository.save(paymentRequestNotification);
    }


    @Override
    public List<OrderConfirmationNotification> findAllNotifications() {
        return orderConfirmationNotificationMongoRepository.findAll();
    }

    @Override
    public Page<PaymentRequestNotification> findAllPaymentRequestNotifications(int page, int size) {
        Pageable pr = PageRequest.of(page, size);
        Page<PaymentRequestNotification> paymentRequestNotificationPage
                = paymentRequestNotificationRepository.findAll(pr);


        return new PageImpl<>(paymentRequestNotificationPage.getContent(), pr, paymentRequestNotificationPage.getTotalElements());
    }

    @Override
    public Page<OrderConfirmationNotification> findAllOrderConfirmationNotifications(int page, int size) {
        Pageable pr = PageRequest.of(page, size);

        Page<OrderConfirmationNotification> orderConfirmationNotificationPage =
                orderConfirmationNotificationMongoRepository.findAll(pr);

        return new PageImpl<>(orderConfirmationNotificationPage.getContent(), pr, orderConfirmationNotificationPage.getTotalElements());
    }
}
