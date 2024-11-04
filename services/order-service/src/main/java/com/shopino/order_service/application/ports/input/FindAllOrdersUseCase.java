package com.shopino.order_service.application.ports.input;

import com.shopino.order_service.domain.dto.response.OrderResponseDto;

import java.util.List;

public interface FindAllOrdersUseCase {
    List<OrderResponseDto> findAllOrders();
}
