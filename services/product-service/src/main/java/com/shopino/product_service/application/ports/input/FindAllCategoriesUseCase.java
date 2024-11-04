package com.shopino.product_service.application.ports.input;

import com.shopino.product_service.domain.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;


public interface FindAllCategoriesUseCase {
    List<Category> findAllCategories();
}
