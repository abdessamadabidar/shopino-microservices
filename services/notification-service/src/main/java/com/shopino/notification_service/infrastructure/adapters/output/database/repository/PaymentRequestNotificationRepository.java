package com.shopino.notification_service.infrastructure.adapters.output.database.repository;

import org.shared.PaymentRequestNotification;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PaymentRequestNotificationRepository extends MongoRepository<PaymentRequestNotification, String> {
}
