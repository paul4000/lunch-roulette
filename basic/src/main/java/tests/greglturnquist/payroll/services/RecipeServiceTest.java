package tests.greglturnquist.payroll.services;

import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.services.RecipeService;
import org.junit.jupiter.api.Test;

/**
 * Created by Paulina on 13.01.2018.
 */
public class RecipeServiceTest {

    private RecipeService recipeService = new RecipeService();

    @Test
    void test()
    {
        Iterable<Recipe> allRecipes = recipeService.getAllRecipes();

        System.out.println();

    }}
