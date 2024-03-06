package com.yakupselami.spring6restmvc.mappers;

import com.yakupselami.spring6restmvc.entities.Customer;
import com.yakupselami.spring6restmvc.model.CustomerDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T11:26:33+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer customerDtoToCustomer(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.customerId( dto.getCustomerId() );
        customer.customerName( dto.getCustomerName() );
        customer.version( dto.getVersion() );
        customer.createdDate( dto.getCreatedDate() );
        customer.lastModifiedDate( dto.getLastModifiedDate() );

        return customer.build();
    }

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.customerName( customer.getCustomerName() );
        customerDTO.customerId( customer.getCustomerId() );
        customerDTO.version( customer.getVersion() );
        customerDTO.createdDate( customer.getCreatedDate() );
        customerDTO.lastModifiedDate( customer.getLastModifiedDate() );

        return customerDTO.build();
    }
}
