package com.sg.recipe.recipe.bootstrap;

import com.sg.recipe.recipe.model.*;
import com.sg.recipe.recipe.repositories.CategoryRepository;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import com.sg.recipe.recipe.repositories.UOMRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UOMRepository uomRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UOMRepository uomRepository) {
        this.recipeRepository = recipeRepository;
        this.uomRepository=uomRepository;
        this.categoryRepository=categoryRepository;

    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
    private void  loadData(){
        Recipe recipe=new Recipe();
        recipe.setCookTime(10).setDescription("Grill Chicken");
        recipe.setDifficulty(Difficulty.MODERATE);
        recipe.setDirections("EAST");
        Optional<Category> categoryOption=categoryRepository.findByDescription("Fast Food");
        Optional<UnitOfMeasure> uomOption=uomRepository.findByUom("Pinch");
        System.out.println("categoryOption = "+categoryOption.get());
        System.out.println("uomOption = "+uomOption.get());

        recipe.getCategories().add(categoryOption.get());
        recipe.setPrepTime(12).setServings(20);
        Ingredient balsamicIngredient=new Ingredient();
        balsamicIngredient.setAmount(new BigDecimal(120.50)).setDescription("cup balsamic vinegar");
        balsamicIngredient.setUom(uomOption.get());
        Ingredient lemonIngredient=new Ingredient();
        lemonIngredient.setAmount(new BigDecimal(110.50)).setDescription("Juice of 1 lemon");
        lemonIngredient.setUom(uomOption.get());
        Ingredient oliveoilIngredient=new Ingredient();
        oliveoilIngredient.setAmount(new BigDecimal(10.50)).setDescription("olive oil");
        oliveoilIngredient.setUom(uomOption.get());
        recipe.getIngredients().add(balsamicIngredient);
        balsamicIngredient.setRecipe(recipe);
        recipe.getIngredients().add(lemonIngredient);
        lemonIngredient.setRecipe(recipe);
        recipe.getIngredients().add(oliveoilIngredient);
        oliveoilIngredient.setRecipe(recipe);
        Notes notes=new Notes();
        notes.setRecipeNotes("cook gentely ..");
        notes.setRecipe(recipe);
        recipe.setNotes(notes);


        Category cat=new Category();
        cat.setDescription("South Thali");
        Set catSet=new HashSet<Category>();
        catSet.add(recipe);
        cat.setRecipes(catSet);
        recipe.getCategories().add(cat);
//        categoryOption.get().getRecipes().add(recipe);
        categoryRepository.save(cat);

        recipeRepository.save(recipe);

    }
}
