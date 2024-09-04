package com.study.spring6reactive.repositories;

import com.study.spring6reactive.entities.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
