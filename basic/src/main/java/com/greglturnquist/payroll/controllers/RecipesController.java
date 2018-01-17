package com.greglturnquist.payroll.controllers;

import com.greglturnquist.payroll.recipeUtils.RecipeParser;
import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.recipes.dto.RecipeDTO;
import com.greglturnquist.payroll.services.RandomizeService;
import com.greglturnquist.payroll.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/recipes")
public class RecipesController {

    private final static Logger logger = Logger.getLogger(RecipesController.class.toString());

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RandomizeService randomizeService;

    @GetMapping(path = "/random")
    public ResponseEntity<Recipe> randomRecipe() {
        logger.log(Level.INFO, "Randomizing recipe...");
        Optional<Recipe> recipe = randomizeService.randomRecipe();

        return recipe.map(recipe1 -> new ResponseEntity<>(recipe1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeDTO recipeDTO) {

        logger.log(Level.INFO, "Adding recipe");

        Recipe recipe = null;

        try {
            recipe = RecipeParser.parseToRecipeObj(recipeDTO);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (Optional.ofNullable(recipe).isPresent()) {
            Recipe aRecipe = recipeService.addRecipe(recipe);
            return new ResponseEntity<>(aRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Iterable<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }


    @GetMapping(path = "/share")
    public ResponseEntity<Void> shareRecipe(@RequestParam(value = "username") String username, @RequestParam(value = "recipeId") String recipeId) {

        logger.log(Level.INFO, "Sharing recipe...");

        recipeService.shareRecipe(username, recipeId);



        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
