package com.yakupselami.spring6restmvc.services;


import com.yakupselami.spring6restmvc.model.Beer;
import com.yakupselami.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CustomerService {

    List<Customer> listCustomers();
    Customer getCustomerById(UUID customerId);

    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
