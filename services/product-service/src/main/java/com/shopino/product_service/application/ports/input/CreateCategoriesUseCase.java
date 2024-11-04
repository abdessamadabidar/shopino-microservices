package com.shopino.product_service.application.ports.input;

import com.shopino.product_service.domain.dto.request.CategoryRequest;
import com.shopino.product_service.domain.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CreateCategoriesUseCase {
    List<Category> createCategories(List<CategoryRequest> requests);
}

