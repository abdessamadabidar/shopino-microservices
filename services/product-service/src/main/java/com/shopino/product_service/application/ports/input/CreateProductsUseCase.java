package com.shopino.product_service.application.ports.input;

import com.shopino.product_service.domain.dto.request.ProductRequest;
import com.shopino.product_service.domain.dto.response.ProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CreateProductsUseCase {
    List<ProductResponseDto> createProducts(List<ProductRequest> requests);
}
