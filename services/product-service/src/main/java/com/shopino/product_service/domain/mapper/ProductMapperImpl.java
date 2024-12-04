package com.shopino.product_service.domain.mapper;

import com.shopino.product_service.domain.dto.response.ProductResponseDto;
import com.shopino.product_service.domain.model.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements IProductMapper {

    @Override
    public ProductResponseDto toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto.ProductResponseDtoBuilder productResponseDto = ProductResponseDto.builder();

        productResponseDto.id( product.getId() );
        productResponseDto.name( product.getName() );
        productResponseDto.description( product.getDescription() );
        productResponseDto.imageUrl( product.getImageUrl() );
        productResponseDto.price( product.getPrice() );
        productResponseDto.availableQuantity( product.getAvailableQuantity() );
        productResponseDto.reference( product.getReference() );
        productResponseDto.category( product.getCategory() );

        return productResponseDto.build();
    }

    @Override
    public List<ProductResponseDto> toProductResponses(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponseDto> list = new ArrayList<ProductResponseDto>( products.size() );
        for ( Product product : products ) {
            list.add( toProductResponse( product ) );
        }

        return list;
    }
}
