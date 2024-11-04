package com.shopino.order_service.infrastructure.adapters.output.database;

import com.shopino.order_service.application.ports.output.OrderOutputPort;
import com.shopino.order_service.domain.model.Order;
import com.shopino.order_service.infrastructure.adapters.output.database.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderOutputPort {

    private final OrderJpaRepository repository;

    @Override
    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Page<Order> findAllOrders(int page, int size) {
        Pageable pr = PageRequest.of(page, size);
        Page<Order> orderPage = repository.findAll(pr);
        return new PageImpl<>(orderPage.getContent(), pr, orderPage.getTotalElements());
    }

    @Override
    public List<Order> findAllOrders() {
        return repository.findAll();
    }


}
