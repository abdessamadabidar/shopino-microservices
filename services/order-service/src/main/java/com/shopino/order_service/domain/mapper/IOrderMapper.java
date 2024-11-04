package com.shopino.order_service.domain.mapper;

import com.shopino.order_service.domain.dto.response.OrderResponseDto;
import com.shopino.order_service.domain.model.Order;

import java.util.List;


public interface IOrderMapper {
    OrderResponseDto toOrderResponse(Order order);
    List<OrderResponseDto> toOrderResponses(List<Order> orders);
}


