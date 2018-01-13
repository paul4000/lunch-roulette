package com.greglturnquist.payroll.services;

import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paulina on 12.01.2018.
 */
@Service
public class RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    public Recipe addRecipe(Recipe recipe) {

        return recipesRepository.save(recipe);

    }

    public Iterable<Recipe> getAllRecipes()
    {
        return recipesRepository.findAll();
    }
}
