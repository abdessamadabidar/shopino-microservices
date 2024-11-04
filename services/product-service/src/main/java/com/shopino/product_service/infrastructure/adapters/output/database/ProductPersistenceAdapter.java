package com.shopino.product_service.infrastructure.adapters.output.database;

import com.shopino.product_service.application.ports.output.ProductOutputPort;
import com.shopino.product_service.domain.model.Product;
import com.shopino.product_service.infrastructure.adapters.output.database.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutputPort {
    private final ProductJpaRepository repository;

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Product> findProductById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Page<Product> findAllProducts(int page, int size) {
        Pageable pr = PageRequest.of(page, size);
        Page<Product> productPage = repository.findAll(pr);
        return repository.findAll(pr);
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }
}
