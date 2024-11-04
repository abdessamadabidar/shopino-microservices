package com.shopino.order_service.infrastructure.adapters.output.database.repository;

import com.shopino.order_service.domain.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderLineJpaRepository extends JpaRepository<OrderLine, UUID> {
}
