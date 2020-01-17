package com.sg.recipe.recipe.controller;

import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipe/{id}/show"})
    public  String getRecipe(@PathVariable Long id, Model model){
        model.addAttribute("recipe",recipeService.findById(id));
        return "/recipe/show";
    }
    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        RecipeCommand recipeCommand=new RecipeCommand();
        model.addAttribute("recipe",recipeCommand);
        return "recipe/newForm";
    }
    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand=recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe",recipeCommand);
        return "recipe/newForm";
    }
    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand model){
    RecipeCommand savedRecipeCommand=recipeService.saveRecipeCommand(model);
    return "redirect:/recipe/"+savedRecipeCommand.getId()+"/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id, Model model){
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
