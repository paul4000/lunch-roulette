package tests.koziolpaulina.foodmanager.services;

import com.koziolpaulina.foodmanager.recipes.Recipe;
import com.koziolpaulina.foodmanager.services.RecipeService;
import org.junit.jupiter.api.Test;


public class RecipeServiceTest {

    private RecipeService recipeService = new RecipeService();

    @Test
    void test()
    {
        Iterable<Recipe> allRecipes = recipeService.getAllRecipes();

        System.out.println();

    }}
