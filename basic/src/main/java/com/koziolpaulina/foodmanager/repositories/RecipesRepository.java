package com.koziolpaulina.foodmanager.repositories;

import com.koziolpaulina.foodmanager.recipes.Recipe;
import org.springframework.data.repository.CrudRepository;


public interface RecipesRepository extends CrudRepository<Recipe, Long> {

}
