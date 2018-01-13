package com.greglturnquist.payroll.recipes;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Paulina on 12.01.2018.
 */
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Step> steps;

    private LocalDateTime lastDraw;

    public Recipe() {}

    public Recipe(String name, Set<Ingredient> ingredients, Set<Step> steps, LocalDateTime lastDraw) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.lastDraw = lastDraw;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Step> getSteps() {
        return steps;
    }

    public void setSteps(Set<Step> steps) {
        this.steps = steps;
    }

    public LocalDateTime getLastDraw() {
        return lastDraw;
    }

    public void setLastDraw(LocalDateTime lastDraw) {
        this.lastDraw = lastDraw;
    }

    public void addIngredient(Ingredient ingredient){
        if (ingredient != null) {
            if (ingredients == null) ingredients = new HashSet<>();
            ingredients.add(ingredient);
            ingredient.setRecipe(this);
        }
    }

    public void addStep(Step step){

        if (step != null) {
            if (steps == null) {
                steps = new HashSet<>();
            }
            steps.add(step);
            step.setRecipe(this);
        }
    }

}
