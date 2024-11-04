package com.shopino.payment_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shared.CustomerResponseDto;
import org.shared.PaymentMethod;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDto
{
    private Double amount;
    private PaymentMethod paymentMethod;
    private UUID orderId;
    private CustomerResponseDto customer;
    private String orderReference;
}
