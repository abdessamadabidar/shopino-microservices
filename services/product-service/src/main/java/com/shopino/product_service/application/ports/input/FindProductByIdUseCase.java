package com.shopino.product_service.application.ports.input;

import com.shopino.product_service.domain.dto.response.ProductResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface FindProductByIdUseCase {
    Optional<ProductResponseDto> findProductById(UUID id);
}
