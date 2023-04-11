package com.training.springlombok.service;

import com.training.springlombok.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}
