package com.sg.recipe.recipe.controller;

import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {
    IndexController indexController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }
    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndexData() {
        Set<Recipe> recipes=new HashSet<Recipe>();
        recipes.add(new Recipe().setDescription("chicken"));
        recipes.add(new Recipe().setDescription("Panner"));
        when(recipeService.getRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);
        String response = indexController.getIndexData(model);
        assertEquals(response, "index", "get index data invoked");
        verify(recipeService,times(1)).getRecipes();
        
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
    }
@Test
    void getRecipe() throws Exception {
    MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();
    mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk())
            .andExpect(view().name("show"));
    }

    @Test
    void deleteById() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/recipe/1/delete")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}