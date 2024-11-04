package com.shopino.order_service.domain.mapper;

import com.shopino.order_service.domain.openfeign.customer.CustomerClient;
import com.shopino.order_service.domain.openfeign.product.ProductClient;
import com.shopino.order_service.domain.dto.response.OrderLineResponseDto;
import com.shopino.order_service.domain.dto.response.OrderResponseDto;
import com.shopino.order_service.domain.model.Order;
import com.shopino.order_service.domain.model.OrderLine;
import lombok.RequiredArgsConstructor;
import org.shared.CustomerResponseDto;
import org.shared.ProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderMapper implements IOrderMapper {

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    @Override
    public OrderResponseDto toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        Optional<CustomerResponseDto> customerResponse = customerClient.findCustomerById(order.getCustomerId());


        OrderResponseDto.OrderResponseDtoBuilder orderResponseDto = OrderResponseDto.builder();

        if (customerResponse.isPresent()) {
            orderResponseDto.id(order.getId());
            orderResponseDto.reference(order.getReference());
            orderResponseDto.totalAmount(order.getTotalAmount());
            orderResponseDto.paymentMethod(order.getPaymentMethod());
            orderResponseDto.customer(customerResponse.get());
            orderResponseDto.orderLines(toOrderLineResponseDtoList(order.getOrderLines()));
            orderResponseDto.createdDate(order.getCreatedDate());
            orderResponseDto.lastModifiedDate(order.getLastModifiedDate());
        }

        return orderResponseDto.build();
    }

    @Override
    public List<OrderResponseDto> toOrderResponses(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponseDto> list = new ArrayList<OrderResponseDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toOrderResponse( order ) );
        }

        return list;
    }

    protected OrderLineResponseDto toOrderLineResponseDto(OrderLine orderLine) {
        if ( orderLine == null ) {
            return null;
        }

        Optional<ProductResponseDto> productResponse = productClient.findProductById(orderLine.getProductId());

        OrderLineResponseDto.OrderLineResponseDtoBuilder orderLineResponseDto = OrderLineResponseDto.builder();


        if (productResponse.isPresent()) {
            orderLineResponseDto.id(orderLine.getId());
            orderLineResponseDto.product(productResponse.get());
            orderLineResponseDto.quantity(orderLine.getQuantity());
        }

        return orderLineResponseDto.build();
    }

    protected List<OrderLineResponseDto> toOrderLineResponseDtoList(List<OrderLine> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderLineResponseDto> list1 = new ArrayList<OrderLineResponseDto>( list.size() );
        for ( OrderLine orderLine : list ) {
            list1.add( toOrderLineResponseDto( orderLine ) );
        }

        return list1;
    }
}
