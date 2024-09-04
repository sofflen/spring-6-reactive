package com.study.spring6reactive.services;

import com.study.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {
    Flux<BeerDTO> getAllBeers();
}
