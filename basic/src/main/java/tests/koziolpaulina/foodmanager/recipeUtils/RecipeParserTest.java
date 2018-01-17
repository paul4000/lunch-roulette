package tests.koziolpaulina.foodmanager.recipeUtils;

import com.koziolpaulina.foodmanager.recipeUtils.RecipeParser;
import com.koziolpaulina.foodmanager.recipes.Ingredient;
import com.koziolpaulina.foodmanager.recipes.Recipe;
import com.koziolpaulina.foodmanager.recipes.dto.RecipeDTO;
import com.koziolpaulina.foodmanager.recipes.Step;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;


public class RecipeParserTest {

    private RecipeDTO recipeDTO = new RecipeDTO();

    private void initCorrectly()
    {
        recipeDTO.setIngredients("marchew:2\n" +
                "brokuł:1\n" +
                "kurczak:3");

        recipeDTO.setSteps("1:Krok pierwszy. \n" +
                "2:Krok drugi. \n" +
                "3:Krok trzeci. ");
    }

    private void initWrong(){

        recipeDTO.setIngredients("marchew:2\n" +
                "brokuł:byzyzyz\n" +
                "kurczak:3");

        recipeDTO.setSteps("1:Krok pierwszy. \n" +
                "2:Krok drugi. \n" +
                "3:Krok trzeci. ");
    }

    @Test
    void should_correctly_parse_RecipeDTO() throws NumberFormatException {
        //given
        initCorrectly();

        //when
        Recipe recipe = RecipeParser.parseToRecipeObj(recipeDTO);

        //then

        //ingredients
        assertThat(recipe.getIngredients())
                .hasSize(3)
                .containsExactlyInAnyOrder(new Ingredient("marchew", 2),
                        new Ingredient("brokuł", 1),
                        new Ingredient("kurczak", 3));

        //steps
        assertThat(recipe.getSteps())
                .hasSize(3)
                .containsExactlyInAnyOrder(new Step(1L, "Krok pierwszy. "),
                        new Step(2L, "Krok drugi. "),
                        new Step(3L, "Krok trzeci. "));
    }

    @Test
    void should_throw_number_format_exception(){
        //given
        initWrong();

        //then

        //when
        assertThatCode( () -> RecipeParser.parseToRecipeObj(recipeDTO))
                .isInstanceOf(NumberFormatException.class);
    }

}
