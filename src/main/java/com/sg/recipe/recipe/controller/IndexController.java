package com.sg.recipe.recipe.controller;

import com.sg.recipe.recipe.model.Category;
import com.sg.recipe.recipe.model.UnitOfMeasure;
import com.sg.recipe.recipe.repositories.CategoryRepository;
import com.sg.recipe.recipe.repositories.UOMRepository;
import com.sg.recipe.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {
   private final RecipeService recipeService;
    public IndexController(RecipeService recipeService){
        this.recipeService=recipeService;
    }
    @GetMapping("/")
    public String getIndexData(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
