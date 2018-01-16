package com.greglturnquist.payroll.recipeUtils;

import com.greglturnquist.payroll.recipes.Ingredient;
import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.recipes.dto.RecipeDTO;
import com.greglturnquist.payroll.recipes.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Paulina on 12.01.2018.
 */
public class RecipeParser {

    private static String patternForStep = "^[0-9]*:[a-zA-Z0-9\\s]*$";

    public static Recipe parseToRecipeObj(RecipeDTO recipeDTO) throws NumberFormatException {

        Recipe resultRecipe = new Recipe();

        resultRecipe.setName(recipeDTO.getName());

        Set<Ingredient> ingredients;

        String ingredientsExtracted = recipeDTO.getIngredients();

        String[] splitIngredients = ingredientsExtracted.split("\\n");

        ingredients = Stream.of(splitIngredients)
                .map(RecipeParser::parseIngredient).collect(Collectors.toSet());

        //ingredients.forEach(resultRecipe::addIngredient);
        resultRecipe.setIngredients(ingredients);

        String stepsExtracted = recipeDTO.getSteps();

        String[] splitSteps = stepsExtracted.split("\\n");

        List<String> groupedSteps = Stream.of(splitSteps).collect(splitCollector());

        Set<Step> stepSet = parseSteps(groupedSteps);

        //stepSet.forEach(resultRecipe::addStep);
        resultRecipe.setSteps(stepSet);

        return resultRecipe;
    }

    private static Set<Step> parseSteps(List<String> groupedSteps) {
        return groupedSteps.stream()
                .map(step -> {
                    String[] exactStep = step.split(":");

                    if(exactStep.length < 2 ) throw new IllegalArgumentException("Empty step!");

                    Step newStep = new Step();
                    newStep.setSequenceNumber(Long.parseLong(exactStep[0]));
                    newStep.setDescription(exactStep[1]);

                    return newStep;
                }).collect(Collectors.toSet());
    }

    private static Ingredient parseIngredient(String ingredient) {
        String[] exactIngredient = ingredient.split(":");

        Ingredient newIngredient = new Ingredient();

        newIngredient.setName(exactIngredient[0]);

        String quantity = exactIngredient.length == 2 ? exactIngredient[1] : "0";

        newIngredient.setQuantity(Integer.parseInt(quantity));

        return newIngredient;
    }

    public static Collector<String, List<String>, List<String>>
    splitCollector(){
        final List<String> temp = new ArrayList<>();
        Pattern pattern = Pattern.compile("^[0-9]*:[a-zA-Z0-9\\s\\W]*$");

        return Collector.of( ArrayList::new,
                (result, lineOfStep) -> {
                    if(pattern.matcher(lineOfStep).matches()){
                        if (!temp.isEmpty())
                        {
                            result.add(temp.stream().collect(Collectors.joining(" ")));
                        }
                        temp.clear();
                        temp.add(lineOfStep);
                    }else{
                        temp.add(lineOfStep);
                    }
                },
                (l1, l2) -> null,
                result ->{
                    if(!temp.isEmpty()){
                        result.add(temp.stream().collect(Collectors.joining(" ")));
                    }
                    return result;
                }
        );
    }


}
