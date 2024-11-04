package com.shopino.order_service.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shared.PaymentMethod;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private UUID customerId;
    private PaymentMethod paymentMethod;
    private Map<UUID, Integer> orderItems;

}
