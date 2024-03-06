package com.yakupselami.spring6restmvc.repositories;

import com.yakupselami.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void saveNewCustomer(){
       Customer savedCustomer = customerRepository.save(Customer.builder().name("Adalynn Pope").build());

       assertThat(savedCustomer.getId()).isNotNull();
    }

}