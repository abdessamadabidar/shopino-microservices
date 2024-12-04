package com.shopino.order_service.domain.openfeign.payment;

import org.shared.PaymentRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${openfeign.route.payments-url}"
)
@Component
public interface PaymentClient {

    @PostMapping
    void requestOrderPayment(@RequestBody PaymentRequestDto request);
}
