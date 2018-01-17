package com.greglturnquist.payroll.repositories;

import com.greglturnquist.payroll.recipes.Recipe;
import org.springframework.data.repository.CrudRepository;


public interface RecipesRepository extends CrudRepository<Recipe, Long> {

}
