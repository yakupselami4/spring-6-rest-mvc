package com.yakupselami.spring6restmvc.controller;

import com.yakupselami.spring6restmvc.entities.Beer;
import com.yakupselami.spring6restmvc.entities.Customer;
import com.yakupselami.spring6restmvc.mappers.CustomerMapper;
import com.yakupselami.spring6restmvc.model.BeerDTO;
import com.yakupselami.spring6restmvc.model.CustomerDTO;
import com.yakupselami.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController controller;

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerMapper customerMapper;



    @Test
    void testDeleteByIdNotFound(){
        assertThrows(NotFoundException.class, ()->{
            controller.deleteCustomerById(UUID.randomUUID());
        });
    }
    @Rollback
    @Transactional
    @Test
    void deleteByIdFound(){
        Customer customer = repository.findAll().get(0);

        ResponseEntity responseEntity = controller.deleteCustomerById(customer.getCustomerId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(repository.findById(customer.getCustomerId()).isEmpty());
//        Customer foundCustomer = repository.findById(customer.getCustomerId()).get();
//        assertThat(foundCustomer).isNotNull();
    }
    @Test
    void testUpdateExistingCustomer(){
        Customer customer = repository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerId(null);
        customerDTO.setVersion(null);
        final String customerName= "Updated";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = controller.updateCustomerById(customer.getCustomerId(),customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));


        Customer updatedCustomer = repository.findById(customer.getCustomerId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName);
    }



    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, ()->{
            controller.updateCustomerById(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void testSaveNewCustomer(){
        CustomerDTO customerDTO  = CustomerDTO.builder()
                .customerName("Jón Snorrason")
                .build();

        ResponseEntity responseEntity = controller.handlePost(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = repository.findById(savedUUID).get();
        assertThat(customer).isNotNull();
    }
    @Test
    void testGetById(){
        Customer customer = repository.findAll().get(0);

        Optional<CustomerDTO> dto =  controller.getCustomerById(customer.getCustomerId());

        assertThat(dto).isNotNull();
    }
    @Test
    void testCustomerIdNotFound(){
        assertThrows(NotFoundException.class, ()->{
            controller.getCustomerById(UUID.randomUUID());
        });
    }

    @Test
    void testListCustomers(){
        List<CustomerDTO> dtos = controller.listCustomers();
        assertThat(dtos.size()).isEqualTo(5);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList(){
        repository.deleteAll();
        List<CustomerDTO> dtos = controller.listCustomers();
        assertThat(dtos.size()).isEqualTo(0);
    }

}