package com.greglturnquist.payroll.recipes;

/**
 * Created by Paulina on 12.01.2018.
 */
public class RecipeDTO {

    private String ingredients;
    private String steps;

    public RecipeDTO(){};

    public RecipeDTO(String ingredients, String steps) {
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
