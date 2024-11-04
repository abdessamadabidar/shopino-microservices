package com.shopino.order_service.application.ports.input;


import com.shopino.order_service.domain.model.OrderLine;

public interface CreateOrderLineUseCase {
    OrderLine createOrderLine(OrderLine orderLine);

}
