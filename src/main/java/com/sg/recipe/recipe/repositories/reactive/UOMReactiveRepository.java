package com.sg.recipe.recipe.repositories.reactive;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.sg.recipe.recipe.model.UnitOfMeasure;

import reactor.core.publisher.Mono;

public interface UOMReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String>{

	 Mono<UnitOfMeasure> findByUom(String uom); 
}

