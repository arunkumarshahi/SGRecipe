package com.sg.recipe.recipe.bootstrap;

import com.sg.recipe.recipe.model.*;
import com.sg.recipe.recipe.repositories.CategoryRepository;
import com.sg.recipe.recipe.repositories.IngredientRepository;
import com.sg.recipe.recipe.repositories.RecipeRepository;
import com.sg.recipe.recipe.repositories.UOMRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UOMRepository uomRepository;
    private final IngredientRepository ingredientRepository;
    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UOMRepository uomRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.uomRepository=uomRepository;
        this.categoryRepository=categoryRepository;

        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        recipeRepository.deleteAll();
        loadData();
    }
    private void  loadData(){
        Recipe recipe=new Recipe();
        recipe.setCookTime(10).setDescription("Grill Chicken");
        recipe.setDifficulty(Difficulty.MODERATE);
        recipe.setDirections("EAST");
        Category category1=new Category();
        category1.setDescription("FastFood");
        UnitOfMeasure uom1=new UnitOfMeasure();
        uom1.setUom("Pinch");
        uom1=uomRepository.save(uom1);
        category1=categoryRepository.save(category1);
        System.out.println("category1 = "+category1);
        System.out.println("uom1 = "+uom1);
//        Optional<UnitOfMeasure> uomOption=uomRepository.findByUom("Pinch");
//        Optional<Category> categoryOption=categoryRepository.findByDescription("FastFood");
//
//        System.out.println("categoryOption = "+categoryOption);
//        System.out.println("uomOption = "+uomOption);

        recipe.getCategories().add(category1);
        recipe.setPrepTime(12).setServings(20);
        Ingredient balsamicIngredient=new Ingredient();
        balsamicIngredient.setAmount(new BigDecimal(120.50)).setDescription("cup balsamic vinegar");
        balsamicIngredient.setUom(uom1);
        Ingredient lemonIngredient=new Ingredient();
        lemonIngredient.setAmount(new BigDecimal(110.50)).setDescription("Juice of 1 lemon");
        lemonIngredient.setUom(uom1);
        Ingredient oliveoilIngredient=new Ingredient();
        oliveoilIngredient.setAmount(new BigDecimal(10.50)).setDescription("olive oil");
        oliveoilIngredient.setUom(uom1);
        balsamicIngredient=ingredientRepository.save(balsamicIngredient);
        lemonIngredient=ingredientRepository.save(lemonIngredient);
        oliveoilIngredient=ingredientRepository.save(oliveoilIngredient);
        recipe.getIngredients().add(balsamicIngredient);
//        balsamicIngredient.setRecipe(recipe);
        recipe.getIngredients().add(lemonIngredient);
//        lemonIngredient.setRecipe(recipe);
        recipe.getIngredients().add(oliveoilIngredient);
//        oliveoilIngredient.setRecipe(recipe);
        Notes notes=new Notes();
        notes.setRecipeNotes("cook gentely ..");
//        notes.setRecipe(recipe);
        recipe.setNotes(notes);


//        Category cat=new Category();
//        cat.setDescription("South Thali");
//        Set catSet=new HashSet<Category>();
//        catSet.add(recipe);
//        cat.setRecipes(catSet);
//        recipe.getCategories().add(cat);
//        categoryOption.get().getRecipes().add(recipe);
//        categoryRepository.save(cat);
        System.out.println("########### uomOption = #################");
        recipe = recipeRepository.save(recipe);
        System.out.println("########### uomOption = #################"+recipe);

    }
}
