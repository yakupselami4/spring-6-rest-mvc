package com.yakupselami.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakupselami.spring6restmvc.entities.Customer;
import com.yakupselami.spring6restmvc.model.BeerDTO;
import com.yakupselami.spring6restmvc.model.CustomerDTO;
import com.yakupselami.spring6restmvc.services.CustomerService;
import com.yakupselami.spring6restmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CustomerController.class)

class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CustomerController customerController;
    @MockBean
    CustomerService customerService;
    @Captor
    ArgumentCaptor<CustomerDTO> customerArgumentCaptor;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp(){
        customerServiceImpl = new CustomerServiceImpl();
    }



    @Test
    void testCreateCustomerNullCustomerName() throws Exception{
        CustomerDTO customerDTO = CustomerDTO.builder().build();

        given(customerService.saveNewCustomer(any(CustomerDTO.class))).willReturn(customerServiceImpl.listCustomers().get(1));

        MvcResult mvcResult = mockMvc.perform(post(customerController.CUSTOMER_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()",is(2)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @Test
    void testCreateNewCustomer() throws Exception {

        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);
        customer.setVersion(null);
        customer.setCustomerId(null);

        given(customerService.saveNewCustomer(any(CustomerDTO.class))).willReturn(customerServiceImpl.listCustomers().get(1));

        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }


    @Test
    void testPatchCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);

        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("customerName", "New Name");

        mockMvc.perform(patch("/api/v1/customer/" + customer.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(status().isNoContent());

        verify(customerService).patchCustomerById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());

        assertThat(customer.getCustomerId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customerMap.get("customerName")).isEqualTo(customerArgumentCaptor.getValue().getCustomerName());
    }

    @Test
    void testDeleteBeer() throws Exception{
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);

        given(customerService.deleteCustomerById(any())).willReturn(true);


        mockMvc.perform(delete("/api/v1/customer/"+customer.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(customerService).deleteCustomerById(uuidArgumentCaptor.capture());

        assertThat(customer.getCustomerId()).isEqualTo(uuidArgumentCaptor.getValue());
    }


    @Test
    void testUpdateCustomer() throws Exception{
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(put("/api/v1/customer/"+customer.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());
        verify(customerService).updateCustomerById(any(UUID.class), any(CustomerDTO.class));
    }


    @Test
    void testListCustomers() throws Exception{

        given(customerService.listCustomers()).willReturn(customerServiceImpl.listCustomers());

        mockMvc.perform(get("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getCustomerById() throws Exception{


        CustomerDTO testCustomer = customerServiceImpl.listCustomers().get(0);

        given(customerService.getCustomerById(testCustomer.getCustomerId())).willReturn(Optional.of(testCustomer));


        mockMvc.perform(get("/api/v1/customer/" + testCustomer.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testCustomer.getCustomerId().toString())))
                .andExpect(jsonPath("$.customerName", is(testCustomer.getCustomerName())));
//
    }
}