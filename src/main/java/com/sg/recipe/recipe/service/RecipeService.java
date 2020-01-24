package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipes();

    public Recipe findById(String id);

    public RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(String id);
    void deleteById(String id);
    void deleteAll();
}
