package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.converters.RecipeCommandToRecipe;
import com.sg.recipe.recipe.converters.RecipeToRecipeCommand;
import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<Recipe>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe recipe = recipeCommandToRecipe.convert(command);
        log.info("data ----- "+recipe.getNotes());
        return recipeToRecipeCommand.convert(recipeRepository.save(recipe));
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(recipeRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
   recipeRepository.deleteById(id);
    }
}
