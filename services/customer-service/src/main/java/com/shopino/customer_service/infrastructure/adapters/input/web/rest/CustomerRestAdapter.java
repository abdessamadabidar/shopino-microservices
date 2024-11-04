package com.shopino.customer_service.infrastructure.adapters.input.web.rest;

import com.shopino.customer_service.application.ports.input.FindAllCustomersUseCase;
import com.shopino.customer_service.application.ports.input.FindCustomerByIdUseCase;
import com.shopino.customer_service.application.ports.input.GetPaginatedCustomersUseCase;
import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import com.shopino.customer_service.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerRestAdapter {
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final FindAllCustomersUseCase findAllCustomersUseCase;
    private final GetPaginatedCustomersUseCase getPaginatedCustomersUseCase;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return ResponseEntity.ok(findAllCustomersUseCase.findAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Optional<CustomerResponseDto>> getCustomerById(
            @PathVariable("customerId") UUID customerId
    ) {
        return ResponseEntity.ok(findCustomerByIdUseCase.findCustomerById(customerId));
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity<PageResponse<CustomerResponseDto>> getPaginatedCustomers(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    )
    {
        return  ResponseEntity.ok(getPaginatedCustomersUseCase.getPaginatedCustomers(page, size));
    }
}
