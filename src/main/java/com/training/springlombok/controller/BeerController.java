package com.training.springlombok.controller;

import com.training.springlombok.model.BeerDTO;
import com.training.springlombok.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BeerDTO> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    public BeerDTO getBeerByID(@PathVariable("beerId") UUID beerId) {
        log.info("In BeerContoller");
        return beerService.getBeerById(beerId);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDTO beer){
        BeerDTO savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beer){
        beerService.updateBeer(beerId,  beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteByID(@PathVariable("beerId") UUID beerId){
        beerService.deleteByID(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{beerId}")
    public ResponseEntity updatePatchBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO){
        beerService.updatePatchBeer(beerId, beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
