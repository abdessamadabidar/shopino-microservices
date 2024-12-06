package org.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;

}