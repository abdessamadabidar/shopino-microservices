package com.shopino.order_service.application.ports.input;

import com.shopino.order_service.domain.dto.request.OrderRequest;
import com.shopino.order_service.domain.dto.response.OrderResponseDto;

public interface MakeOrderUseCase {
    OrderResponseDto makeOrder(OrderRequest request) throws InterruptedException;
}
