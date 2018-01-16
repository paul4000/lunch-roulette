package com.greglturnquist.payroll.services;

import com.google.common.collect.Iterables;
import com.greglturnquist.payroll.auth.SecurityService;
import com.greglturnquist.payroll.recipeUtils.UsersRegister;
import com.greglturnquist.payroll.recipes.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * Created by Paulina on 16.01.2018.
 */
@Service
public class RandomizeService {

    @Autowired
    private UsersRegister usersRegister;

    @Autowired
    private SecurityService securityService;

    public Optional<Recipe> randomRecipe() {

        String loggedInUsername = securityService.findLoggedInUsername();

        Iterable<Recipe> allRecipes = usersRegister.getUser(loggedInUsername).getRecipes();

        if (Iterables.isEmpty(allRecipes)) return Optional.empty();

        Random rand = new Random();

        Recipe recipe = Iterables.get(allRecipes, rand.nextInt(Iterables.size(allRecipes)));

        return Optional.of(recipe);

    }

}
