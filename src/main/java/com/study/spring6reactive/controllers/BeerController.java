package com.study.spring6reactive.controllers;

import com.study.spring6reactive.model.BeerDTO;
import com.study.spring6reactive.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beers";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    public Flux<BeerDTO> getAllBeers() {
        return beerService.getAllBeers();
    }
}
