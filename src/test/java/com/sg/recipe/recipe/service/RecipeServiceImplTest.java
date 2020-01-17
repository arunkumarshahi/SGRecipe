package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.converters.RecipeCommandToRecipe;
import com.sg.recipe.recipe.converters.RecipeToRecipeCommand;
import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    RecipeService recipeService;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipes=new HashSet<>();
        recipes.add(new Recipe().setDescription("Test1"));
        recipes.add(new Recipe().setDescription("Test12"));
        recipes.add(new Recipe().setDescription("Test13"));
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(recipeService.getRecipes().size(),3,"test executed for get recipe");
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    void findById() {
       Recipe recipe= new Recipe().setDescription("Test1");
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(recipe));
        assertEquals(recipeService.findById(100L).getDescription(),"Test1");
    }

    @Test
    void deleteById() {
        Long idToBeDeleted=2L;
        recipeRepository.deleteById(idToBeDeleted);
        recipeService.deleteById(idToBeDeleted);
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}