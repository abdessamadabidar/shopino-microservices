package com.shopino.product_service.infrastructure.adapters.output.database.repository;

import com.shopino.product_service.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
}
