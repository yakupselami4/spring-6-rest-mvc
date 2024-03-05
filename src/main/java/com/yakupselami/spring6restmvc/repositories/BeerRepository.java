package com.yakupselami.spring6restmvc.repositories;

import com.yakupselami.spring6restmvc.entities.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {


}
