package com.sg.recipe.recipe.repositories;

import com.sg.recipe.recipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
