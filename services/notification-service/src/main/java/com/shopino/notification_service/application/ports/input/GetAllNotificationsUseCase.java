package com.shopino.notification_service.application.ports.input;


import org.shared.OrderConfirmationNotification;

import java.util.List;

public interface GetAllNotificationsUseCase {
    List<OrderConfirmationNotification> findAllNotifications();

}
