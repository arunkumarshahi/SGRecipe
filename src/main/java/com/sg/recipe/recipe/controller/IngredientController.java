package com.sg.recipe.recipe.controller;

import com.sg.recipe.recipe.command.IngredientCommand;
import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.service.IngredientService;
import com.sg.recipe.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
@Slf4j
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping({"/recipe/{id}/ingredients"})
    public String getIngredients(@PathVariable String id, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }

    @GetMapping({"/recipe/{id}/ingredient/{ingredientId}/show"})
    public String getRecipe(@PathVariable Long id, @PathVariable Long ingredientId, Model model) {
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(id, ingredientId);
        model.addAttribute("ingredient", ingredientCommand);
        return "/recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable Long recipeId, Model model) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/newForm";
    }

    @GetMapping("/recipe/{id}/ingredient/{ingredientId}/update")
    public String updateRecipe(@PathVariable Long id, @PathVariable Long ingredientId, Model model) {
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(id, ingredientId);
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/newForm";
    }
    @PostMapping("/recipe/{id}/ingredient")
    public String saveOrUpdateIngredient(@PathVariable Long id,@ModelAttribute IngredientCommand model){
        IngredientCommand savedRecipeCommand=ingredientService.saveIngradientCommand(model);
        return "redirect:/recipe/"+id+"/ingredient/"+savedRecipeCommand.getId()+"/show";
    }

    @GetMapping("/recipe/{id}/ingredient/{ingredientId}/delete")
    public String deleteRecipeById(@PathVariable String id, @PathVariable String ingredientId, Model model) {
        ingredientService.deleteById(Long.valueOf(ingredientId));
        return "redirect:/recipe/{id}/ingredients";
    }
}
