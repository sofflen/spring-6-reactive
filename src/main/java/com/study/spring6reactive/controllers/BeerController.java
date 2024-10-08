package com.study.spring6reactive.controllers;

import com.study.spring6reactive.model.BeerDTO;
import com.study.spring6reactive.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String ROOT_URL = "http://localhost:8080";
    public static final String BEER_PATH = "/api/v2/beers";
    public static final String BEER_ID_PATH = BEER_PATH + "/{id}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    public Flux<BeerDTO> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping(BEER_ID_PATH)
    public Mono<BeerDTO> getBeerById(@PathVariable("id") Integer id) {
        return beerService
                .getBeerById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(BEER_PATH)
    public Mono<ResponseEntity<Void>> createBeer(@Validated @RequestBody BeerDTO beerDTO) {
        return beerService.saveNewBeer(beerDTO)
                .map(savedDto ->
                        ResponseEntity
                                .created(UriComponentsBuilder
                                        .fromHttpUrl(ROOT_URL + BEER_PATH + "/" + savedDto.getId())
                                        .build().toUri())
                                .build()
                );
    }

    @PutMapping(BEER_ID_PATH)
    public Mono<ResponseEntity<Void>> updateBeer(@PathVariable("id") Integer id,
                                                 @Validated @RequestBody BeerDTO beerDTO) {
        return beerService
                .updateBeer(id, beerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(updatedDto -> ResponseEntity.noContent().build());
    }

    @PatchMapping(BEER_ID_PATH)
    public Mono<ResponseEntity<Void>> patchBeer(@PathVariable("id") Integer id,
                                                @Validated @RequestBody BeerDTO beerDTO) {
        return beerService
                .patchBeer(id, beerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(patchedDto -> ResponseEntity.noContent().build());
    }

    @DeleteMapping(BEER_ID_PATH)
    public Mono<ResponseEntity<Void>> deleteBeer(@PathVariable("id") Integer id) {
        return beerService
                .getBeerById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(beerDTO -> beerService.deleteBeer(beerDTO.getId()))
                .thenReturn(ResponseEntity.noContent().build());
    }
}
