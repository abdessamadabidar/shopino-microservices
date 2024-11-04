package com.shopino.payment_service.application.ports.output;


import com.shopino.payment_service.domain.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface PaymentOutputPort {
    Payment savePaymentRequest(Payment payment);
    Page<Payment> findAllCustomers(int page, int size);
}
