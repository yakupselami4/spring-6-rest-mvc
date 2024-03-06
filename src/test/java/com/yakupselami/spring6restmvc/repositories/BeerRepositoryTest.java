package com.yakupselami.spring6restmvc.repositories;

import com.yakupselami.spring6restmvc.entities.Beer;
import com.yakupselami.spring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("Blue Beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("1212455")
                        .price(new BigDecimal(20.99))
                        .build());
        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();



    }
}