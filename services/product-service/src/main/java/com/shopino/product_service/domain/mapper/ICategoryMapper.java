package com.shopino.product_service.domain.mapper;

import com.shopino.product_service.domain.dto.request.CategoryRequest;
import com.shopino.product_service.domain.model.Category;

import java.util.List;

public interface ICategoryMapper {

    Category toCategory(CategoryRequest request);
    List<Category> toCategories(List<CategoryRequest> requests);

}
