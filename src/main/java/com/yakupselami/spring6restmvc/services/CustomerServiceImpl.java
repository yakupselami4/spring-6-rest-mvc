package com.yakupselami.spring6restmvc.services;


import com.yakupselami.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);
        if (StringUtils.hasText(customer.getCustomerName())){
            existing.setCustomerName(customer.getCustomerName());
        }
    }

    private Map<UUID, Customer> customerMap;


    public CustomerServiceImpl(){
        this.customerMap= new HashMap<>();

        Customer customer1 = Customer.builder()
                .customerId(UUID.randomUUID())
                .version(1)
                .customerName("Yakup Selami Öztürk")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .customerId(UUID.randomUUID())
                .version(1)
                .customerName("Hüseyin Çalışkan")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .customerId(UUID.randomUUID())
                .version(1)
                .customerName("İsmail Furkan Türkoğlu")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getCustomerId(),customer1);
        customerMap.put(customer2.getCustomerId(),customer2);
        customerMap.put(customer3.getCustomerId(),customer3);
    }
    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        return customerMap.get(customerId);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {

        Customer savedCustomer = Customer.builder()
                .customerId(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(customer.getCreatedDate())
                .lastModifiedDate(customer.getLastModifiedDate())
                .build();

        customerMap.put(savedCustomer.getCustomerId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);
        existing.setCustomerName(customer.getCustomerName());

    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }
}
