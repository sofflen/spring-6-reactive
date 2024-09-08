package com.study.spring6reactive.services;

import com.study.spring6reactive.mappers.BeerMapper;
import com.study.spring6reactive.model.BeerDTO;
import com.study.spring6reactive.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> getAllBeers() {
        return beerRepository
                .findAll()
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> getBeerById(Integer id) {
        return beerRepository
                .findById(id)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO) {
        return beerRepository
                .save(beerMapper.beerDTOToBeer(beerDTO))
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer id, BeerDTO beerDTO) {
        return beerRepository
                .findById(id)
                .map(foundBeer -> {
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setPrice(beerDTO.getPrice());
                    foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    foundBeer.setUpc(beerDTO.getUpc());

                    return foundBeer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> patchBeer(Integer id, BeerDTO beerDTO) {
        return beerRepository
                .findById(id)
                .map(foundBeer -> {
                    if (hasText(beerDTO.getBeerName()))
                        foundBeer.setBeerName(beerDTO.getBeerName());
                    if (hasText(beerDTO.getBeerStyle()))
                        foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    if (beerDTO.getPrice() != null)
                        foundBeer.setPrice(beerDTO.getPrice());
                    if (beerDTO.getQuantityOnHand() != null)
                        foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    if (hasText(beerDTO.getUpc()))
                        foundBeer.setUpc(beerDTO.getUpc());

                    return foundBeer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<Void> deleteBeer(Integer id) {
        return beerRepository.deleteById(id);
    }
}
