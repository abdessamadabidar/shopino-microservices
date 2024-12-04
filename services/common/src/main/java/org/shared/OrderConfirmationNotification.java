package org.shared;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "order_notifications")
public class OrderConfirmationNotification {
    @Id
    private String id;
    private String orderReference;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponseDto customer;
    private List<ProductResponseDto> products;
    private LocalDateTime orderDate;
}
