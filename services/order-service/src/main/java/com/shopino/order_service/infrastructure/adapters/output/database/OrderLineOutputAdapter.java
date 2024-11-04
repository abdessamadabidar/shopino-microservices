package com.shopino.order_service.infrastructure.adapters.output.database;

import com.shopino.order_service.application.ports.output.OrderLineOutputPort;
import com.shopino.order_service.domain.model.OrderLine;
import com.shopino.order_service.infrastructure.adapters.output.database.repository.OrderLineJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderLineOutputAdapter implements OrderLineOutputPort {
    private final OrderLineJpaRepository repository;

    @Override
    public OrderLine saveOrderLine(OrderLine orderLine) {
        return repository.save(orderLine);
    }
}
