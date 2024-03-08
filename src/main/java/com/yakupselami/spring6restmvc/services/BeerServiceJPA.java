package com.yakupselami.spring6restmvc.services;

import com.yakupselami.spring6restmvc.entities.Beer;
import com.yakupselami.spring6restmvc.mappers.BeerMapper;
import com.yakupselami.spring6restmvc.model.BeerDTO;
import com.yakupselami.spring6restmvc.model.BeerStyle;
import com.yakupselami.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInInventory) {
        List<Beer> beerList;

        if(StringUtils.hasText(beerName)){
            beerList = ListBeerByName(beerName);
        } else if (!StringUtils.hasText(beerName)&& beerStyle != null) {
            beerList = listBeersByStyle(beerStyle);
        }else if (StringUtils.hasText(beerName)&& beerStyle != null) {
            beerList = listBeerByNameAndStyle(beerName,beerStyle);
        }
        else{
            beerList = beerRepository.findAll();
        }

        if(showInInventory!= null && !showInInventory){
            beerList.forEach(beer -> beer.setQuantityOnHand(null));
        }

        return beerList.stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    private List<Beer> listBeerByNameAndStyle(String beerName, BeerStyle beerStyle) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(beerName,beerStyle);
    }

    public List<Beer> ListBeerByName(String beerName){
        return beerRepository.findAllByBeerNameIsLikeIgnoreCase("%"+beerName+"%");
    }
    public List<Beer> listBeersByStyle(BeerStyle beerStyle){
        return beerRepository.findAllByBeerStyle(beerStyle);
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();


        beerRepository.findById(beerId).ifPresentOrElse(foundBeer->{
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            atomicReference.set(Optional.of(beerMapper.beerToBeerDto(beerRepository.save(foundBeer))));
        }, ()->{
            atomicReference.set(Optional.empty());
        });
        return atomicReference.get();
    }

    @Override
    public boolean deleteBeerById(UUID beerId) {
        if (beerRepository.existsById(beerId)){
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

    }
}
