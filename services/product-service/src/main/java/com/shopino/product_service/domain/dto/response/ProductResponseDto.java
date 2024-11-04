package com.shopino.product_service.domain.dto.response;

import com.shopino.product_service.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int availableQuantity;
    private String reference;
    private Category category;
}
