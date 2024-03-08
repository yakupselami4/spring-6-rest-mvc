package com.yakupselami.spring6restmvc.services;

import com.yakupselami.spring6restmvc.model.BeerDTO;
import com.yakupselami.spring6restmvc.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface BeerService {

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);


    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    boolean deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}
