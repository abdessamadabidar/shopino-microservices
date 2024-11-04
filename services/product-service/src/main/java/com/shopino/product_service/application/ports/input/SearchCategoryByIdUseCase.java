package com.shopino.product_service.application.ports.input;

import com.shopino.product_service.domain.model.Category;

import java.util.UUID;

public interface SearchCategoryByIdUseCase {
    Category findCategoryById(UUID id);
}
