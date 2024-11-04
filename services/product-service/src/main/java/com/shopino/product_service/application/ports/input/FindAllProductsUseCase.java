package com.shopino.product_service.application.ports.input;


import com.shopino.product_service.domain.dto.response.ProductResponseDto;

import java.util.List;

public interface FindAllProductsUseCase {
    List<ProductResponseDto> findAllProducts();
}
