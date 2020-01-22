package com.sg.recipe.recipe.controller;

import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.exceptions.NotFoundExceptionx;
import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import com.sg.recipe.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {
    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
 @InjectMocks
 RecipeController recipeController;
 @Mock
    RecipeRepository recipeRepository;
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
    Recipe recipe=new Recipe().setDescription("Panner");
    recipe.setId(1L);
    when(recipeService.findById(anyLong())).thenReturn(recipe);
    MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
    mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk())
            .andExpect(view().name("/recipe/show"));
    }


    @Test
    void getRecipeNotFoundException() throws Exception {
        Recipe recipe=new Recipe().setDescription("Panner");
        when(recipeService.findById(anyLong())).thenThrow(new NotFoundExceptionx());
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).
                setControllerAdvice(new ControllerExceptionHandler()).
                build();
        mockMvc.perform(get("/recipe/1/show")).andExpect(status().isNotFound())
                .andExpect(view().name("exception/404Error"));
    }

    @Test
    void getRecipeBadRequestException() throws Exception {
        Recipe recipe=new Recipe().setDescription("Panner");
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).
                setControllerAdvice(new ControllerExceptionHandler()).build();
        mockMvc.perform(get("/recipe/1A/show")).andExpect(status().isBadRequest())
                .andExpect(view().name("exception/404Error"));
    }

    @Test
    void deleteById() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/1/delete")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void saveOrUpdateRecipe() throws Exception {
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setPrepTime(1005);
        recipeCommand.setDescription("A");
        recipeCommand.setCookTime(10);
        recipeCommand.setServings(20);
        when(recipeService.saveRecipeCommand(recipeCommand)).thenReturn(recipeCommand);
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(post("/recipe")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" +recipeCommand.getId()  + "/show"));
    }

    //saveOrUpdateRecipe --
}