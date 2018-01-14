package com.greglturnquist.payroll.controllers;

import com.greglturnquist.payroll.recipeUtils.RecipeParser;
import com.greglturnquist.payroll.recipes.Recipe;
import com.greglturnquist.payroll.recipes.RecipeDTO;
import com.greglturnquist.payroll.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * Created by Paulina on 12.01.2018.
 */
@RestController
@RequestMapping(path = "/recipes")
public class RecipesController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping(path = "/random")
    public ResponseEntity<Recipe> randomRecipe()
    {
        Optional<Recipe> recipe = recipeService.randomRecipe();

        return recipe.map(recipe1 -> new ResponseEntity<>(recipe1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeDTO recipeDTO) {

        Recipe recipe = null;

        try{
            recipe = RecipeParser.parseToRecipeObj(recipeDTO);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        if(Optional.ofNullable(recipe).isPresent()){
            Recipe aRecipe = recipeService.addRecipe(recipe);
            return new ResponseEntity<>(aRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Iterable<Recipe> getAllRecipes()
    {
        return recipeService.getAllRecipes();
    }



}
