package com.training.springlombok.controller;

import com.training.springlombok.model.Beer;
import com.training.springlombok.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@Slf4j
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    public Beer getBeerByID(UUID id) {
        log.info("In BeerContoller");
        return beerService.getBeerById(id);
    }
}
