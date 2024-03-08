package com.yakupselami.spring6restmvc.services;

import com.yakupselami.spring6restmvc.mappers.CustomerMapper;
import com.yakupselami.spring6restmvc.model.CustomerDTO;
import com.yakupselami.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID customerId) {
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository.
                findById(customerId).orElse(null)));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDtoToCustomer(customer)));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer->{
            foundCustomer.setName(customer.getCustomerName());
            atomicReference.set(Optional.of(customerMapper.customerToCustomerDTO(customerRepository.save(foundCustomer))));
        },()-> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public boolean deleteCustomerById(UUID customerId) {
        if (customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

    }

}


