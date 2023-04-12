package com.training.springlombok.service;

import com.training.springlombok.model.BeerDTO;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers();

    BeerDTO getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    void updateBeer(UUID beerId, BeerDTO beer);

    void deleteByID(UUID beerId);

    void updatePatchBeer(UUID beerId, BeerDTO beer);
}
