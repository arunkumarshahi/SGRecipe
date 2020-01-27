package com.sg.recipe.recipe.repositories;

import com.sg.recipe.recipe.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UOMRepository extends CrudRepository<UnitOfMeasure, String> {
    Optional<UnitOfMeasure> findByUom(String uom);
}


