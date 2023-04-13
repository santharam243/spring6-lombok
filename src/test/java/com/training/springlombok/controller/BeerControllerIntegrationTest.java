package com.training.springlombok.controller;

import com.training.springlombok.entity.Beer;
import com.training.springlombok.exception.NotFoundException;
import com.training.springlombok.mapper.BeerMapper;
import com.training.springlombok.model.BeerDTO;
import com.training.springlombok.repository.BeerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerIntegrationTest {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Test
    void testSize(){
        List<BeerDTO> beerList = beerController.listBeers();
        Assertions.assertThat(beerList.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @Rollback
    void testEmptyCondition(){
        beerRepository.deleteAll();
        List<BeerDTO> beerList = beerController.listBeers();
        Assertions.assertThat(beerList.size()).isEqualTo(0);
    }

    @Test
    void testGetById(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerController.getBeerByID(beer.getId()).get();
        Assertions.assertThat(beerDTO).isNotNull();
    }

    @Test
    void testNotFoundException(){
        assertThrows(NotFoundException.class, () -> {
           beerController.getBeerByID(UUID.randomUUID());
        });
    }

    @Test
    @Transactional
    @Rollback
    void testBeerInsert(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Mc Donalds").build();

        ResponseEntity responseEntity = beerController.saveNewBeer(beerDTO);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        Assertions.assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] location = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID beerId = UUID.fromString(location[4]);

        Beer beer = beerRepository.findById(beerId).get();
        Assertions.assertThat(beer).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    void testUpdateBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);
        final String beerName = beer.getBeerName();
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        beerDTO.setBeerName(beerName);
        ResponseEntity responseEntity = beerController.updateBeer(beer.getId(), beerDTO);

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        Assertions.assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testNotFoundExceptionForUpdate(){
        assertThrows(NotFoundException.class, () -> {
           beerController.updateBeer(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteById(){
        Beer beer = beerRepository.findAll().get(0);
        ResponseEntity responseEntity = beerController.deleteByID(beer.getId());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        Assertions.assertThat(beerRepository.findById(beer.getId())).isEmpty();
    }

    @Test
    void testNotFoundExceptionForDelete(){
        assertThrows(NotFoundException.class, () ->beerController.deleteByID(UUID.randomUUID()));
    }

}