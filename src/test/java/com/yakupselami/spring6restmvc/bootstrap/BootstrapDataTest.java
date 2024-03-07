package com.yakupselami.spring6restmvc.bootstrap;

import com.yakupselami.spring6restmvc.repositories.BeerRepository;
import com.yakupselami.spring6restmvc.repositories.CustomerRepository;
import com.yakupselami.spring6restmvc.services.BeerCsvService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapDataTest {

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BeerCsvService beerCsvService;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp(){
        bootstrapData= new BootstrapData(beerRepository,customerRepository, beerCsvService);
    }

}