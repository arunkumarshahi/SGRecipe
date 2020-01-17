package com.sg.recipe.recipe.service;

import com.sg.recipe.recipe.command.RecipeCommand;
import com.sg.recipe.recipe.converters.RecipeCommandToRecipe;
import com.sg.recipe.recipe.converters.RecipeToRecipeCommand;
import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class RecipeServiceITTest {
    public static final String A_ROASTED_PANEER_WITH_XTRA_CHEASE = "A roasted Paneer with xtra chease";
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    RecipeService recipeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecipes() {
    }

    @Test
    void findById() {
    }

    @Test
    @Transactional
    void saveRecipeCommand() {
        Iterable<Recipe> recipes=recipeRepository.findAll();
        Recipe testRecipe=recipes.iterator().next();
//        Recipe testRecipe=new Recipe();
        log.info("recipe Command recived ....",testRecipe);
        testRecipe.setDescription(A_ROASTED_PANEER_WITH_XTRA_CHEASE);
        RecipeCommand recipeCommand =recipeToRecipeCommand.convert(testRecipe);
        RecipeCommand recipeCommand1=recipeService.saveRecipeCommand(recipeCommand);
        log.debug("recipeCommand1",recipeCommand1);
        assertEquals(recipeCommand1.getDescription(),A_ROASTED_PANEER_WITH_XTRA_CHEASE);
    }
}