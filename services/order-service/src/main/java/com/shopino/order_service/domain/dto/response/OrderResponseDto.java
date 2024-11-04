package com.shopino.order_service.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shared.CustomerResponseDto;
import org.shared.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private UUID id;
    private String reference;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponseDto customer;
    private List<OrderLineResponseDto> orderLines;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
