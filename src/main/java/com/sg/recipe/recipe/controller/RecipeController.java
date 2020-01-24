package com.sg.recipe.recipe.controller;

import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;
    private static final String RECIPE_RECIPEFORM_URL = "recipe/newForm";
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipe/{id}/show"})
    public String getRecipe(@PathVariable String id, Model model) {
        log.info("getRecipe calld for .. == " + id);
        model.addAttribute("recipe", recipeService.findById(id));
        log.info("getRecipe return calld for .. == " + id);
        return "/recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        RecipeCommand recipeCommand = new RecipeCommand();
        model.addAttribute("recipe", recipeCommand);
        return "recipe/newForm";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(id);
        model.addAttribute("recipe", recipeCommand);
        return "recipe/newForm";
    }

    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                log.info("error occured" + error.toString());
            });
            return  RECIPE_RECIPEFORM_URL;
        }
        log.info("saveOrUpdateRecipe for .."+model);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(model);
        log.info("saved Recipe  for .."+savedRecipeCommand);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id, Model model) {
        recipeService.deleteById(String.valueOf(id));
        return "redirect:/";
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundExceptionx.class)
//    public ModelAndView handleNotFound(Exception exception) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception",exception);
//        modelAndView.addObject("exceptionCode",404);
//        modelAndView.setViewName("exception/404Error");
//        return modelAndView;
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NumberFormatException.class)
//    public ModelAndView handleNumberFormatException(Exception exception) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception",exception);
//        modelAndView.addObject("exceptionCode",400);
//        modelAndView.setViewName("exception/404Error");
//        return modelAndView;
//    }
}
