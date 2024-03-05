package com.yakupselami.spring6restmvc.controller;

import com.yakupselami.spring6restmvc.entities.Beer;
import com.yakupselami.spring6restmvc.mappers.BeerMapper;
import com.yakupselami.spring6restmvc.model.BeerDTO;
import com.yakupselami.spring6restmvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
class BeerControllerIT {

    @Autowired
    BeerController controller;
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerMapper beerMapper;


    @Test
    void testDeleteByIdNotFound(){
        assertThrows(NotFoundException.class, ()->{
            controller.deleteBeerById(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void deleteByIdFound(){
        Beer beer = beerRepository.findAll().get(0);

        ResponseEntity responseEntity = controller.deleteBeerById(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(beerRepository.findById(beer.getId()).isEmpty());

    }
    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, ()->{
            controller.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Test
    void testUpdateExistingBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName= "Updated";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = controller.updateById(beer.getId(),beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));


        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }
    @Rollback
    @Transactional
    @Test
    void testSaveNewBeer(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Interstellar Hop Odyssey Galactic")
                .build();

        ResponseEntity responseEntity = controller.handlePost(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();

    }
    @Test
    void testBeerIdNotFound(){
        assertThrows(NotFoundException.class, ()->{
            controller.getBeerById(UUID.randomUUID());
        });
    }
    @Test
    void testGetById(){
        Beer beer = beerRepository.findAll().get(0);

        Optional<BeerDTO> dto = controller.getBeerById(beer.getId());

        assertThat(dto).isNotNull();
    }
    @Test
    void testListBeers(){
        List<BeerDTO> dtos = controller.listBeers();

        assertThat(dtos.size()).isEqualTo(5);

    }
    @Rollback
    @Transactional
    @Test
    void testEmptyList(){
        beerRepository.deleteAll();
        List<BeerDTO> dtos = controller.listBeers();
        assertThat(dtos.size()).isEqualTo(0);
    }
}