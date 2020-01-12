package com.sg.recipe.recipe.repositories;

import com.sg.recipe.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String desc);
}
