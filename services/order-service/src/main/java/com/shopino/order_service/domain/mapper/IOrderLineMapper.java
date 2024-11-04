package com.shopino.order_service.domain.mapper;

import com.shopino.order_service.domain.dto.response.OrderLineResponseDto;
import com.shopino.order_service.domain.model.OrderLine;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderLineMapper {
    OrderLineResponseDto toOrderLineResponse(OrderLine orderLine);
    List<OrderLineResponseDto> toOrderLineResponses(List<OrderLine> orderLines);
}
