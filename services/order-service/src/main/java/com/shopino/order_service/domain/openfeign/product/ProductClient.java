package com.shopino.order_service.domain.openfeign.product;


import org.shared.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8000/api/v1/products"
)
@Component
public interface ProductClient {


    @GetMapping
    List<ProductResponseDto> findAllProducts();

    @GetMapping("/{productId}")
    Optional<ProductResponseDto> findProductById(@PathVariable("productId") UUID productId);
}
