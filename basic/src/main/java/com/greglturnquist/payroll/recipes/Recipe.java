package com.greglturnquist.payroll.recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greglturnquist.payroll.auth.login.User;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ElementCollection(targetClass = Ingredient.class, fetch = FetchType.EAGER)
    @JoinTable(name = "ingredients")
    @JoinColumn(name = "recipe_tid", referencedColumnName = "id")
    private Set<Ingredient> ingredients;

    @ElementCollection(targetClass = Step.class, fetch = FetchType.EAGER)
    @JoinTable(name = "steps")
    @JoinColumn(name = "recipe_tid", referencedColumnName = "id")
    private Set<Step> steps;

    private LocalDateTime lastDraw;

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.MERGE,CascadeType.REFRESH
    })
    @JoinTable(name = "recipe_user",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<User> users;

    public Recipe() {
    }

    public Recipe(String name, Set<Ingredient> ingredients, Set<Step> steps, LocalDateTime lastDraw, Set<User> users) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.lastDraw = lastDraw;
        this.users = users;
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

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user)
    {
        if(users == null){
            users = new HashSet<>();
        }
        users.add(user);
        user.getRecipes().add(this);
    }
    public void removeUser(User user)
    {
        users.remove(user);
        user.getRecipes().remove(this);
    }


}
