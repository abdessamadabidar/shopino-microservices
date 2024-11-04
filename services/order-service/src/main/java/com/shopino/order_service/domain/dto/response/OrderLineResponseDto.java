package com.shopino.order_service.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shared.ProductResponseDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineResponseDto {
    private UUID id;
    private int quantity;
    private ProductResponseDto product;
}
