package com.shopino.product_service.infrastructure.adapters.input.web.rest;

import com.shopino.product_service.application.ports.input.FindAllProductsUseCase;
import com.shopino.product_service.application.ports.input.FindProductByIdUseCase;
import com.shopino.product_service.application.ports.input.GetPaginatedProductsUseCase;
import com.shopino.product_service.domain.dto.response.ProductResponseDto;
import com.shopino.product_service.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestAdapter {

    private final FindProductByIdUseCase findProductByIdUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final GetPaginatedProductsUseCase getPaginatedProductsUseCase;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        return ResponseEntity.ok(findAllProductsUseCase.findAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductResponseDto>> getProductById(
            @PathVariable("productId") UUID productId
            ) {
        return ResponseEntity.ok(findProductByIdUseCase.findProductById(productId));
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity<PageResponse<ProductResponseDto>> getPaginatedProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseEntity.ok(getPaginatedProductsUseCase.getPaginatedProducts(page, size));
    }
}
