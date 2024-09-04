package com.study.spring6reactive.services;

import com.study.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {

    Flux<BeerDTO> getAllBeers();

    Mono<BeerDTO> getBeerById(Integer id);
}
