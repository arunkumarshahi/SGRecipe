package com.sg.recipe.recipe.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.sg.recipe.recipe.model.Category;
import com.sg.recipe.recipe.model.Recipe;
import com.sg.recipe.recipe.repositories.reactive.CategoryReactiveRepository;
import com.sg.recipe.recipe.repositories.reactive.RecipeReactiveRepository;

@DataMongoTest

public class CategoryReactiveRepositoryIT {
	private static final String A_MONGO_RECIPE = "A Mongo Recipe";
	@Autowired
	CategoryReactiveRepository catReactiveRepository;
	
	public void setup() {
		catReactiveRepository.deleteAll().block();
		
	}
	@Test
	public void testRecipeSave() {
		Category category=new Category();
		category.setDescription(A_MONGO_RECIPE);
		catReactiveRepository.save(category).block();
		Long count = catReactiveRepository.count().block();
		assertEquals(1L, count);
	}
	@Test
	public void testRecipeByDesc() {
		Category category=new Category();
		category.setDescription(A_MONGO_RECIPE);
		catReactiveRepository.save(category).block();
		String desc = catReactiveRepository.findByDescription(A_MONGO_RECIPE).block().getDescription();
		assertEquals(desc, A_MONGO_RECIPE);
	}
	

}
