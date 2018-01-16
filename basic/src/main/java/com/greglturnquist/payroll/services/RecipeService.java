package com.greglturnquist.payroll.services;

import com.google.common.collect.Iterables;
import com.greglturnquist.payroll.auth.SecurityServiceImpl;
import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.repositories.RecipesRepository;
import com.greglturnquist.payroll.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * Created by Paulina on 12.01.2018.
 */
@Service
public class RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SecurityServiceImpl securityService;

    public Recipe addRecipe(Recipe recipe) {

        String loggedInUsername = securityService.findLoggedInUsername();
        User userByUsername = usersRepository.findByUsername(loggedInUsername);

        recipe.addUser(userByUsername);

        return recipesRepository.save(recipe);
    }

    public Iterable<Recipe> getAllRecipes() {
        return recipesRepository.findAll();
    }

    public Optional<Recipe> randomRecipe() {

        Iterable<Recipe> allRecipes = getAllRecipes();

        if(Iterables.isEmpty(allRecipes)) return Optional.empty();

        Random rand = new Random();

        Recipe recipe = Iterables.get(allRecipes, rand.nextInt(Iterables.size(allRecipes)));

        return Optional.of(recipe);

    }
}
