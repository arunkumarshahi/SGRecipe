package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.command.IngredientCommand;
import com.sg.recipe.recipe.model.Ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    public void deleteById( Long ingredientId);
    public IngredientCommand saveIngradientCommand(IngredientCommand ingredientCommand);
}
