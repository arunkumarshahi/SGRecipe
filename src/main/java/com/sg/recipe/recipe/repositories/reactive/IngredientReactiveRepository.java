package com.sg.recipe.recipe.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.sg.recipe.recipe.model.Ingredient;
import com.sg.recipe.recipe.model.UnitOfMeasure;

import reactor.core.publisher.Mono;

public interface IngredientReactiveRepository extends ReactiveMongoRepository<Ingredient, String>{

	 //<UnitOfMeasure> findByUom(String uom); 
}

