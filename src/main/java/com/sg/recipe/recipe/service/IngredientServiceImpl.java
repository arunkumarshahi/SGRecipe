package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.command.IngredientCommand;
import com.sg.recipe.recipe.converters.IngredientCommandToIngredient;
import com.sg.recipe.recipe.converters.IngredientToIngredientCommand;
import com.sg.recipe.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.sg.recipe.recipe.model.Ingredient;
import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.model.UnitOfMeasure;
import com.sg.recipe.recipe.repositories.IngredientRepository;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import com.sg.recipe.recipe.repositories.UOMRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientRepository ingredientRepository;
    private final UOMRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, IngredientRepository ingredientRepository, UOMRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientRepository = ingredientRepository;
        this.uomRepository = uomRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        IngredientCommand ingredientCommand = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId() == ingredientId)
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst().orElse(null);
        return ingredientCommand;
    }

    @Override
    public void deleteById(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public IngredientCommand saveIngradientCommand(IngredientCommand ingredientCommand) {
        log.debug("ingredientCommand getRecipeId ::"+ingredientCommand.getRecipeId());
        UnitOfMeasure unitOfMeasure=uomRepository.findById(1L).get();
        ingredientCommand.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure));
        Recipe recipe = recipeRepository.findById(ingredientCommand.getRecipeId()).orElse(null);
        Ingredient ingredient= ingredientCommandToIngredient.convert(ingredientCommand);
        Optional<Ingredient> ingredientOptional=recipe.getIngredients().stream().filter(ingredient1 -> ingredient1.getId()==ingredient.getId()).findFirst();
        log.debug("list retrieved ingredient -->"+recipe.getIngredients());
        log.debug("single retrieved ingredient -->"+ingredient);

        if(ingredientOptional.isPresent()){
            Ingredient optionalIngredient=ingredientOptional.get();
            optionalIngredient.setDescription(ingredientCommand.getDescription());
            optionalIngredient.setAmount(ingredientCommand.getAmount());

        }else {
            recipe.getIngredients().add(ingredient);
            ingredient.setRecipe(recipe);
        }
        return ingredientToIngredientCommand.convert(recipeRepository.save(recipe).getIngredients().
                stream().filter(ingredient1 ->
                (ingredient1.getDescription().equalsIgnoreCase(ingredientCommand.getDescription()))).
                findFirst().get());
    }
}
