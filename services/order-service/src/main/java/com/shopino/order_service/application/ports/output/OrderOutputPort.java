package com.shopino.order_service.application.ports.output;

import com.shopino.order_service.domain.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

import java.util.List;

@Component
public interface OrderOutputPort {
    Order saveOrder(Order order);
    Page<Order> findAllOrders(int page, int size);
    List<Order> findAllOrders();
}
