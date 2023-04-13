package com.training.springlombok.mapper;

import com.training.springlombok.entity.Beer;
import com.training.springlombok.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);
}
