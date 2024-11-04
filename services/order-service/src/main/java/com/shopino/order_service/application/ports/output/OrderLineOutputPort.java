package com.shopino.order_service.application.ports.output;

import com.shopino.order_service.domain.model.OrderLine;
import org.springframework.stereotype.Component;

@Component
public interface OrderLineOutputPort {
    OrderLine saveOrderLine(OrderLine orderLine);
}
