package org.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "payment_notifications")
public class PaymentRequestNotification {
    @Id
    private String id;
    private String orderReference;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponseDto customer;
    private LocalDateTime requestDate;
}
