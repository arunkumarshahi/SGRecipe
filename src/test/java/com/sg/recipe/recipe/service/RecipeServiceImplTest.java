package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;
    RecipeService recipeService;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);
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
}