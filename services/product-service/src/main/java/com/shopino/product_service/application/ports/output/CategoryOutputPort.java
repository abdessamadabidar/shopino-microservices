package com.shopino.product_service.application.ports.output;

import com.shopino.product_service.domain.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface CategoryOutputPort {
    Optional<Category> findCategoryById(UUID id);
    Optional<Category> findCategoryByName(String name);

    List<Category> saveAllCategories(List<Category> categories);
    List<Category> findAllCategories();
}
