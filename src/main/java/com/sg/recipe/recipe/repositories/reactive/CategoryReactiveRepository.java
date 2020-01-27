package com.sg.recipe.recipe.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.sg.recipe.recipe.model.Category;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String>{
	Mono<Category> findByDescription(String desc);
}

