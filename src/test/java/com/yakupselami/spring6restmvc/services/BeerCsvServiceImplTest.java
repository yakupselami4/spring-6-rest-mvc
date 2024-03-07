package com.yakupselami.spring6restmvc.services;

import com.yakupselami.spring6restmvc.model.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeerCsvServiceImplTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();


    @Test
    void convertCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("C:\\Users\\Yakup\\Desktop\\courses\\courses\\spring-6-rest-mvc\\spring-6-rest-mvc\\src\\main\\resources\\csv_data\\beers.csv");

        List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);
        System.out.println(recs.size());
        assertThat(recs.size()).isGreaterThan(0);
    }
}