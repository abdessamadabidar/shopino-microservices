package com.shopino.product_service.domain.mapper;

import com.shopino.product_service.domain.dto.response.ProductResponseDto;
import com.shopino.product_service.domain.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductResponseDto toProductResponse(Product product);
    List<ProductResponseDto> toProductResponses(List<Product> products);

}
