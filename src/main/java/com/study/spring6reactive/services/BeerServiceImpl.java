package com.study.spring6reactive.services;

import com.study.spring6reactive.mappers.BeerMapper;
import com.study.spring6reactive.model.BeerDTO;
import com.study.spring6reactive.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> getAllBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDTO);
    }
}
