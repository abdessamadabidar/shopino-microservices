package com.shopino.product_service.infrastructure.adapters.output.database;

import com.shopino.product_service.application.ports.output.CategoryOutputPort;
import com.shopino.product_service.domain.model.Category;
import com.shopino.product_service.infrastructure.adapters.output.database.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryOutputPort {
    private final CategoryJpaRepository repository;


    @Override
    public Optional<Category> findCategoryById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Category> saveAllCategories(List<Category> categories) {
        return repository.saveAll(categories);
    }

    @Override
    public List<Category> findAllCategories() {
        return repository.findAll();
    }
}
