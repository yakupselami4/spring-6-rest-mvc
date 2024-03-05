package com.yakupselami.spring6restmvc.mappers;

import com.yakupselami.spring6restmvc.entities.Customer;
import com.yakupselami.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDTO(Customer customer);


}
