package com.yakupselami.spring6restmvc.services;

import com.yakupselami.spring6restmvc.mappers.BeerMapper;
import com.yakupselami.spring6restmvc.model.BeerDTO;
import com.yakupselami.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceIJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> ListBeers() {
        return null;
    }

    @Override
    public BeerDTO getBeerById(UUID id) {
        return null;
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return null;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {

    }

    @Override
    public void deleteBeerById(UUID beerId) {

    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

    }
}
