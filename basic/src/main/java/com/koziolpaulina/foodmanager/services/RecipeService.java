package com.koziolpaulina.foodmanager.services;

import com.koziolpaulina.foodmanager.auth.SecurityServiceImpl;
import com.koziolpaulina.foodmanager.auth.login.User;
import com.koziolpaulina.foodmanager.recipeUtils.UsersRegister;
import com.koziolpaulina.foodmanager.recipes.Recipe;
import com.koziolpaulina.foodmanager.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    public void shareRecipe(String username, String recipeId){
        User user = usersRegister.getUser(username);

        Recipe recipe = recipesRepository.findOne(Long.valueOf(recipeId));

        recipe.addUser(user);

        usersRegister.refreshUser(user);

        recipesRepository.save(recipe);
    }

}
