package com.shopino.order_service.domain.service;

import com.shopino.order_service.application.ports.input.CreateOrderLineUseCase;
import com.shopino.order_service.application.ports.output.OrderLineOutputPort;
import com.shopino.order_service.domain.model.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService implements CreateOrderLineUseCase {

    private final OrderLineOutputPort orderLineOutputPort;


    @Override
    public OrderLine createOrderLine(OrderLine orderLine) {

        return orderLineOutputPort.saveOrderLine(orderLine);
    }



}
