package com.shopino.product_service.domain.service;

import com.shopino.product_service.application.ports.input.CreateProductsUseCase;
import com.shopino.product_service.application.ports.input.FindAllProductsUseCase;
import com.shopino.product_service.application.ports.input.FindProductByIdUseCase;
import com.shopino.product_service.application.ports.input.GetPaginatedProductsUseCase;
import com.shopino.product_service.application.ports.output.ProductOutputPort;
import com.shopino.product_service.domain.dto.request.ProductRequest;
import com.shopino.product_service.domain.dto.response.ProductResponseDto;
import com.shopino.product_service.domain.exception.ProductAlreadyExists;
import com.shopino.product_service.domain.mapper.IProductMapper;
import com.shopino.product_service.domain.model.Category;
import com.shopino.product_service.domain.model.Product;
import com.shopino.product_service.domain.utils.PageInfo;
import com.shopino.product_service.domain.utils.PageResponse;
import com.shopino.product_service.domain.utils.StringGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements
        CreateProductsUseCase,
        GetPaginatedProductsUseCase,
        FindProductByIdUseCase,
        FindAllProductsUseCase
{
    private final ProductOutputPort productOutputPort;
    private final CategoryServiceImpl categoryService;
    private final IProductMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Override
    public List<ProductResponseDto> createProducts(List<ProductRequest> requests) {

        List<Product> products = new ArrayList<>();
        requests.forEach(
                pr -> {
                    // check whether the product already exist
                    Optional<Product> optionalProduct = productOutputPort
                            .findProductByName(pr.getName());

                    if (optionalProduct.isPresent()) {
                        LOGGER.error("Product {} already exist!", pr.getName());
                        throw new ProductAlreadyExists("Product already exist");
                    }


                    // load category
                    Category category = categoryService.findCategoryById(pr.getCategoryId());

                    Product newProduct = Product
                            .builder()
                            .name(pr.getName())
                            .description(pr.getDescription())
                            .imageUrl(pr.getImageUrl())
                            .price(pr.getPrice())
                            .reference(StringGenerator.generate(9, 5, 14))
                            .availableQuantity(pr.getAvailableQuantity())
                            .category(category)
                            .build();


                    products.add(newProduct);

                });

        List<Product> savedProducts = productOutputPort.saveAllProducts(products);
        return mapper.toProductResponses(savedProducts);
    }

    @Override
    public PageResponse<ProductResponseDto> getPaginatedProducts(int page, int size) {
        Page<Product> productsPage = productOutputPort.findAllProducts(page, size);

        List<ProductResponseDto> mapperProductResponses = mapper
                .toProductResponses(
                        productsPage.getContent()
                );

        return PageResponse.<ProductResponseDto>builder()
                .content(mapperProductResponses)
                .pageInfo(
                        PageInfo
                                .builder()
                                .totalPages(productsPage.getTotalPages())
                                .totalElements(productsPage.getTotalElements())
                                .currentPage(productsPage.getNumber() + 1)
                                .size(productsPage.getSize())
                                .hasNext(productsPage.hasNext())
                                .hasPrevious(productsPage.hasPrevious())
                                .build()
                )
                .build();
    }

    @Override
    public Optional<ProductResponseDto> findProductById(UUID id) {
        return productOutputPort.findProductById(id).map(mapper::toProductResponse);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return mapper.toProductResponses(productOutputPort.findAllProducts());
    }
}
