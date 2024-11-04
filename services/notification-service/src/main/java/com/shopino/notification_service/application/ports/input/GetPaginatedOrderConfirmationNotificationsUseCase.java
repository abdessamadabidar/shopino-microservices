package com.shopino.notification_service.application.ports.input;

import org.shared.OrderConfirmationNotification;
import org.shared.PageResponse;
import org.springframework.data.domain.Page;

public interface GetPaginatedOrderConfirmationNotificationsUseCase {
    PageResponse<OrderConfirmationNotification> getPaginatedOrderConfirmationNotifications(int page, int size);
}
