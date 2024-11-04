package com.shopino.order_service.infrastructure.adapters.input.web.rest;

import com.shopino.order_service.application.ports.input.FindAllOrdersUseCase;
import com.shopino.order_service.application.ports.input.GetPaginatedOrdersUseCase;
import com.shopino.order_service.domain.dto.response.OrderResponseDto;
import com.shopino.order_service.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestAdapter {

    private final FindAllOrdersUseCase findAllOrdersUseCase;
    private final GetPaginatedOrdersUseCase getPaginatedOrdersUseCase;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.of(Optional.ofNullable(findAllOrdersUseCase.findAllOrders()));
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity<PageResponse<OrderResponseDto>> getPaginatedOrders(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseEntity.ok(getPaginatedOrdersUseCase.getPaginatedCustomers(page, size));
    }
}
