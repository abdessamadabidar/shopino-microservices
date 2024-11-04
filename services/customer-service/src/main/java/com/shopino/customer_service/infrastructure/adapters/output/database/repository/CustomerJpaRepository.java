package com.shopino.customer_service.infrastructure.adapters.output.database.repository;

import com.shopino.customer_service.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);
}
