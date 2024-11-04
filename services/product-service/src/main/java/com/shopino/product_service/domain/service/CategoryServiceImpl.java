package com.shopino.product_service.domain.service;

import com.shopino.product_service.application.ports.input.CreateCategoriesUseCase;
import com.shopino.product_service.application.ports.input.FindAllCategoriesUseCase;
import com.shopino.product_service.application.ports.input.SearchCategoryByIdUseCase;
import com.shopino.product_service.application.ports.output.CategoryOutputPort;
import com.shopino.product_service.domain.dto.request.CategoryRequest;
import com.shopino.product_service.domain.mapper.ICategoryMapper;
import com.shopino.product_service.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements SearchCategoryByIdUseCase, FindAllCategoriesUseCase, CreateCategoriesUseCase {
    private final CategoryOutputPort categoryOutputPort;
    private final ICategoryMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Override
    public Category findCategoryById(UUID id) {
        return categoryOutputPort.findCategoryById(id)
                .orElseThrow(
                        () -> {
                            LOGGER.error("Category {} does not exist!", id);
                            return new RuntimeException("Category not found");
                        }
                );
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryOutputPort.findAllCategories();
    }

    @Override
    public List<Category> createCategories(List<CategoryRequest> requests) {
        requests
                .forEach(cr -> {
                    Optional<Category> optional = categoryOutputPort
                            .findCategoryByName(cr.getName());
                    if (optional.isPresent()) {
                        LOGGER.error("Category {} already exist!", cr.getName());
                        throw new RuntimeException("Category already exist");
                    }

                });


        return categoryOutputPort
                .saveAllCategories(
                        mapper.toCategories(requests)
                );
    }
}
