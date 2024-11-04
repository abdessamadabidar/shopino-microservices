package com.shopino.notification_service.application.ports.input;

import org.shared.PageResponse;
import org.shared.PaymentRequestNotification;
import org.springframework.data.domain.Page;

public interface GetPaginatedPaymentNotificationsUseCase {

    PageResponse<PaymentRequestNotification> getPaginatedPaymentNotifications(int page, int size);
}
