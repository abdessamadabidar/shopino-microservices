package com.shopino.notification_service.infrastructure.adapters.output.database.repository;


import org.shared.OrderConfirmationNotification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderConfirmationNotificationMongoRepository extends MongoRepository<OrderConfirmationNotification, String> {
}
