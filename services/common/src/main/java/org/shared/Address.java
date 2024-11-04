package org.shared;


import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address {
    private Long zipCode;
    private String street;
    private Integer houseNumber;
    private String city;
}
