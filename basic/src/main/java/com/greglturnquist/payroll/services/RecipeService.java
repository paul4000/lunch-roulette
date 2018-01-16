package com.greglturnquist.payroll.services;

import com.greglturnquist.payroll.auth.SecurityServiceImpl;
import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.recipeUtils.UsersRegister;
import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Paulina on 12.01.2018.
 */
@Service
public class RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private UsersRegister usersRegister;

    @Autowired
    private SecurityServiceImpl securityService;

    public Recipe addRecipe(Recipe recipe) {

        String loggedInUsername = securityService.findLoggedInUsername();

        recipe.addUser(usersRegister.getUser(loggedInUsername));

        return recipesRepository.save(recipe);
    }

    public Set<Recipe> getAllRecipes() {

        String loggedInUsername = securityService.findLoggedInUsername();

        return usersRegister.getUser(loggedInUsername).getRecipes();

    }

    public Recipe shareRecipe(String username, Recipe recipe){
        //todo : fix sharing recipes
        User user = usersRegister.getUser(username);
        recipe.addUser(user);

        return recipesRepository.save(recipe);
    }

}
