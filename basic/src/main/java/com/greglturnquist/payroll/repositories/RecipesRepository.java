package com.greglturnquist.payroll.repositories;

import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.recipes.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paulina on 12.01.2018.
 */
public interface RecipesRepository extends CrudRepository<Recipe, Long> {

}