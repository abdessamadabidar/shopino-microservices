package com.shopino.payment_service.infrastructure.adapters.output.database.repository;
import com.shopino.payment_service.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<Payment, UUID>  {
}
