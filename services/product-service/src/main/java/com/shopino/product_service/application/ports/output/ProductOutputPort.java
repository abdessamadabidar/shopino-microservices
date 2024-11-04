package com.shopino.product_service.application.ports.output;

import com.shopino.product_service.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface ProductOutputPort {
    List<Product> saveAllProducts(List<Product> products);
    Optional<Product> findProductByName(String name);
    Optional<Product> findProductById(UUID id);
    Page<Product> findAllProducts(int page, int size);
    List<Product> findAllProducts();
}
