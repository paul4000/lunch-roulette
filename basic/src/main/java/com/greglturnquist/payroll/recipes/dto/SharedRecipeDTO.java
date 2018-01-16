package com.greglturnquist.payroll.recipes.dto;

import com.greglturnquist.payroll.recipes.Recipe;

/**
 * Created by Paulina on 17.01.2018.
 */
public class SharedRecipeDTO {

    private String username;
    private Recipe recipe;

    public SharedRecipeDTO(){}

    public SharedRecipeDTO(String username, Recipe recipe) {
        this.username = username;
        this.recipe = recipe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
