package com.shopino.product_service.infrastructure.adapters.output.database.repository;

import com.shopino.product_service.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(String name);
}
