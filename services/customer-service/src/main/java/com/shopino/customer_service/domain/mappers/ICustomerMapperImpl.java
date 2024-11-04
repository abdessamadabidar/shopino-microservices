package com.shopino.customer_service.domain.mappers;

import com.shopino.customer_service.domain.dto.request.CustomerRequestDto;
import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import com.shopino.customer_service.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ICustomerMapperImpl implements ICustomerMapper {

    @Override
    public CustomerResponseDto toCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseDto.CustomerResponseDtoBuilder customerResponseDto = CustomerResponseDto.builder();

        customerResponseDto.id( customer.getId() );
        customerResponseDto.firstName( customer.getFirstName() );
        customerResponseDto.lastName( customer.getLastName() );
        customerResponseDto.email( customer.getEmail() );
        customerResponseDto.phone( customer.getPhone() );
        customerResponseDto.address( customer.getAddress() );

        return customerResponseDto.build();
    }

    @Override
    public List<CustomerResponseDto> toCustomerResponses(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerResponseDto> list = new ArrayList<CustomerResponseDto>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( toCustomerResponse( customer ) );
        }

        return list;
    }

    @Override
    public Customer toCustomer(CustomerRequestDto request) {
        if ( request == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.firstName( request.getFirstName() );
        customer.lastName( request.getLastName() );
        customer.email( request.getEmail() );
        customer.phone( request.getPhone() );
        customer.address( request.getAddress() );

        return customer.build();
    }

    @Override
    public List<Customer> toCustomers(List<CustomerRequestDto> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( requests.size() );
        for ( CustomerRequestDto customerRequestDto : requests ) {
            list.add( toCustomer( customerRequestDto ) );
        }

        return list;
    }
}
