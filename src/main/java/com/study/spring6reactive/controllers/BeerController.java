package com.study.spring6reactive.controllers;

import com.study.spring6reactive.model.BeerDTO;
import com.study.spring6reactive.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beers";
    public static final String BEER_ID_PATH = BEER_PATH + "/{id}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    public Flux<BeerDTO> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping(BEER_ID_PATH)
    public Mono<BeerDTO> getBeerById(@PathVariable("id") Integer id) {
        return beerService.getBeerById(id);
    }
}
