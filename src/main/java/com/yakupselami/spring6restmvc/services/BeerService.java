package com.yakupselami.spring6restmvc.services;

import com.yakupselami.spring6restmvc.model.BeerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface BeerService {

    List<BeerDTO> ListBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    boolean deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}
