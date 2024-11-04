package com.shopino.payment_service.infrastructure.adapters.input.web.rest;


import com.shopino.payment_service.application.ports.input.CreatePaymentRequestUseCase;
import com.shopino.payment_service.application.ports.input.GetPaginatedPaymentsUseCase;
import com.shopino.payment_service.domain.dto.PaymentRequestDto;
import com.shopino.payment_service.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import org.shared.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentRestAdapter {


    private final CreatePaymentRequestUseCase createPaymentRequestUseCase;
    private final GetPaginatedPaymentsUseCase getPaginatedPaymentsUseCase;

    @PostMapping
    public void requestOrderPayment(@RequestBody PaymentRequestDto request) {
        createPaymentRequestUseCase.createPaymentRequest(request);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<PageResponse<Payment>> getPaginatedPayments(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseEntity.ok(getPaginatedPaymentsUseCase.getPaginatedCustomers(page, size));
    }
}
