package com.greglturnquist.payroll.recipes.dto;

import com.greglturnquist.payroll.recipes.Ingredient;

import java.util.List;

/**
 * Created by Paulina on 16.01.2018.
 */
public class ShoppingListDTO {

    private String recipeName;
    private List<Ingredient> ingredients;

    public ShoppingListDTO(){}

    public ShoppingListDTO(String recipeName, List<Ingredient> ingredients) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
