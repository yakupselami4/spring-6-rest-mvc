package com.yakupselami.spring6restmvc.repositories;

import com.yakupselami.spring6restmvc.bootstrap.BootstrapData;
import com.yakupselami.spring6restmvc.entities.Beer;
import com.yakupselami.spring6restmvc.model.BeerStyle;
import com.yakupselami.spring6restmvc.services.BeerCsvServiceImpl;
import com.yakupselami.spring6restmvc.services.BeerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;


    @Test
    void testsGetBeerListByName(){
        List<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");
        assertThat(list.size()).isEqualTo(336);
    }

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