package com.shopino.payment_service.infrastructure.adapters.output.database;

import com.shopino.payment_service.application.ports.output.PaymentOutputPort;
import com.shopino.payment_service.domain.model.Payment;
import com.shopino.payment_service.infrastructure.adapters.output.database.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentOutputPort {

    private final PaymentJpaRepository repository;

    @Override
    public Payment savePaymentRequest(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Page<Payment> findAllCustomers(int page, int size) {
        Pageable pr = PageRequest.of(page, size);
        Page<Payment> paymentPage = repository.findAll(pr);

        return new PageImpl<>(paymentPage.getContent(), pr, paymentPage.getTotalElements());
    }
}
