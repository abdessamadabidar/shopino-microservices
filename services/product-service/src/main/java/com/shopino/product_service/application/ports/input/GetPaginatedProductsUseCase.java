package com.shopino.product_service.application.ports.input;

import com.shopino.product_service.domain.dto.response.ProductResponseDto;
import com.shopino.product_service.domain.utils.PageResponse;

public interface GetPaginatedProductsUseCase {
    PageResponse<ProductResponseDto> getPaginatedProducts(int page, int size);
}
