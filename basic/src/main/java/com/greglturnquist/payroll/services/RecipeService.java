package com.greglturnquist.payroll.services;

import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.recipes.RecipeDTO;
import com.greglturnquist.payroll.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Paulina on 12.01.2018.
 */
public class RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

//    @Autowired
//    private

    public void addRecipe(RecipeDTO recipeDTO){

    }
}
