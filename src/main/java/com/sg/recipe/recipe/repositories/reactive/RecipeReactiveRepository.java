package com.sg.recipe.recipe.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.model.UnitOfMeasure;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String>{

}

