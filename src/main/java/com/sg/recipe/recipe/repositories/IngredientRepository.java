package com.sg.recipe.recipe.repositories;

import com.sg.recipe.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Long>{

}
