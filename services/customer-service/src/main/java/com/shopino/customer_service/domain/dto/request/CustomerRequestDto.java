package com.shopino.customer_service.domain.dto.request;

import com.shopino.customer_service.domain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;

}

